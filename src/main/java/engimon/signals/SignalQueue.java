package engimon.signals;

import java.util.*;

public class SignalQueue {
    public static SignalQueue INSTANCE;
    private Queue<Signal> signalQueue;
    private Map<Class<Signal>, List<SignalListener<Signal>>> listenersRegistry;

    public SignalQueue() {
        if (INSTANCE != null) {
            return;
        }
        INSTANCE = this;
        signalQueue = new LinkedList<>();
        listenersRegistry = new HashMap<>();
    }

    public void registerListener(Class<Signal> eventClass, SignalListener<Signal> signalListener) {
        if (!listenersRegistry.containsKey(eventClass)) {
            List<SignalListener<Signal>> signalListeners = new ArrayList<>();
            listenersRegistry.put(eventClass, signalListeners);
        }
        List<SignalListener<Signal>> signalListeners = listenersRegistry.get(eventClass);
        signalListeners.add(signalListener);
    }

    public void addToQueue(Signal signal) {
        signalQueue.add(signal);
    }

    public void handleFirstInQueue() {
        Signal signal = signalQueue.poll();
        if (signal == null) {
            return;
        }
        List<SignalListener<Signal>> signalListeners = listenersRegistry.get(signal.getClass());
        for (SignalListener<Signal> signalListener : signalListeners) {
            signalListener.invoke(signal);
        }
    }
}
