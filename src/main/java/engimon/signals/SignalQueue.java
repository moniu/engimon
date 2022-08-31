package engimon.signals;

import java.util.*;

public class SignalQueue {
    private static final SignalQueue INSTANCE = new SignalQueue();
    private final Queue<Signal> signalQueue;
    private final Map<Class<? extends Signal>, List<SignalListener>> listenersRegistry;

    public SignalQueue() {
        this.signalQueue = new LinkedList<>();
        this.listenersRegistry = new HashMap<>();
    }

    public static SignalQueue instance() {
        return INSTANCE;
    }

    public static void registerListener(Class<? extends Signal> eventClass, SignalListener signalListener) {
        synchronized (INSTANCE) {
            if (!INSTANCE.listenersRegistry.containsKey(eventClass)) {
                List<SignalListener> signalListeners = new ArrayList<>();
                INSTANCE.listenersRegistry.put(eventClass, signalListeners);
            }
            List<SignalListener> signalListeners = INSTANCE.listenersRegistry.get(eventClass);
            signalListeners.add(signalListener);
        }
    }

    public static void addToQueue(Signal signal) {
        synchronized (INSTANCE) {
            INSTANCE.signalQueue.add(signal);
        }
    }

    public static void handleFirstInQueue() {
        synchronized (INSTANCE) {
            Signal signal = INSTANCE.signalQueue.poll();
            if (signal == null) {
                return;
            }
            List<SignalListener> signalListeners = INSTANCE.listenersRegistry.get(signal.getClass());
            for (SignalListener signalListener : signalListeners) {
                signalListener.invoke(signal);
            }
        }
    }
}
