package engimon.signals;

@FunctionalInterface
public interface SignalListener<SignalType extends Signal> {
    void invoke(SignalType signal);
}
