package concurrency.lesson151114;

import concurrency.utils.Utils;

import java.util.Random;

public class Stayer implements Runnable {
    private static final Random random = new Random();
    private Barrier barrier;

    public Stayer(Barrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("Start " + this);
        Utils.pause(5000 + random.nextInt(5000));
        System.out.println("Finished " + this);
        barrier.await();
        System.out.println("passed barrier " + this);
    }
}
