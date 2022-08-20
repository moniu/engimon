package engimon.signals;

@FunctionalInterface
public interface SignalListener {
    void invoke(Signal signal);
}
