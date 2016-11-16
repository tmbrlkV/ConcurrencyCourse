package concurrency.lesson151114.cyclic_barrier;

import java.util.concurrent.CyclicBarrier;

public class Stadium {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4, () -> System.out.println("Hurry up!"));

        new Thread(new Stayer(barrier)).start();
        new Thread(new Stayer(barrier)).start();
        new Thread(new Stayer(barrier)).start();
        new Thread(new Stayer(barrier)).start();
    }
}
