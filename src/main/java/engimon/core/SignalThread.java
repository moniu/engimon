package engimon.core;

import engimon.signals.SignalQueue;

@SuppressWarnings("InfiniteLoopStatement")
public class SignalThread implements Runnable {
    @Override
    public synchronized void run() {
        while (true) {
            SignalQueue.handleFirstInQueue();
        }
    }
}
