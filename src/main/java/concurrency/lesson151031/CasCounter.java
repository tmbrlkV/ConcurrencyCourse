package concurrency.lesson151031;

public class CasCounter {
    private SimulateCompareAndSwap value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while(v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }
}
