package engimon.signals

import spock.lang.Specification

class SignalQueueTest extends Specification {
    void "Listener is added to the signalQueue and Emmiter invokes it"() {
        given:
        new SignalQueue()
        TestSignal signal = new TestSignal()
        Boolean listenerHasBeenInvoked = false
        SignalQueue.instance().registerListener(TestSignal, ($event) -> { listenerHasBeenInvoked = true })
        when:
        SignalQueue.instance().addToQueue(signal)
        SignalQueue.instance().handleFirstInQueue()
        then:
        listenerHasBeenInvoked
    }

    void "Only listener of given signal type is invoked"() {
        given:
        new SignalQueue()
        Signal signal = new TestSignal()
        Signal signal2 = new TestSignal2()
        Boolean listenerHasBeenInvoked = false
        Boolean otherListenerHasBeenInvoked = false
        SignalQueue.instance().registerListener(TestSignal, (TestSignal dudu) -> listenerHasBeenInvoked = true)
        SignalQueue.instance().registerListener(TestSignal2, ($signal) -> otherListenerHasBeenInvoked = true)
        when:
        SignalQueue.instance().addToQueue(signal2)
        SignalQueue.instance().handleFirstInQueue()
        then:
        !listenerHasBeenInvoked
        otherListenerHasBeenInvoked
    }
}
