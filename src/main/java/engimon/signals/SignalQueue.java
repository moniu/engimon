package engimon.signals;

import java.util.*;

public class SignalQueue {
    public static SignalQueue INSTANCE;
    private Queue<Signal> signalQueue;
    private Map<Class<? extends Signal>, List<SignalListener>> listenersRegistry;

    public SignalQueue() {
        if (INSTANCE != null) {
            return;
        }
        INSTANCE = this;
        signalQueue = new LinkedList<>();
        listenersRegistry = new HashMap<>();
    }

    public synchronized void registerListener(Class<? extends Signal> eventClass, SignalListener signalListener) {
        if (!listenersRegistry.containsKey(eventClass)) {
            List<SignalListener> signalListeners = new ArrayList<>();
            listenersRegistry.put(eventClass, signalListeners);
        }
        List<SignalListener> signalListeners = listenersRegistry.get(eventClass);
        signalListeners.add(signalListener);
    }

    public synchronized void addToQueue(Signal signal) {
        signalQueue.add(signal);
    }

    public synchronized void handleFirstInQueue() {
        Signal signal = signalQueue.poll();
        if (signal == null) {
            return;
        }
        List<SignalListener> signalListeners = listenersRegistry.get(signal.getClass());
        for (SignalListener signalListener : signalListeners) {
            signalListener.invoke(signal);
        }
    }
}
