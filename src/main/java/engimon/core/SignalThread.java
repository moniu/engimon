package engimon.core;

import engimon.signals.SignalQueue;

@SuppressWarnings("InfiniteLoopStatement")
public class SignalThread implements Runnable {
    static final SignalQueue queue = SignalQueue.INSTANCE;

    @Override
    public void run() {
        while (true) {
            queue.handleFirstInQueue();
        }
    }
}
