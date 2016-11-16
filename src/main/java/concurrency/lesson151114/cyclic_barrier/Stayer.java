package concurrency.lesson151114.cyclic_barrier;

import concurrency.utils.Utils;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Stayer implements Runnable {
    private static final Random random = new Random();
    private CyclicBarrier barrier;

    public Stayer(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("Start " + this);
        Utils.pause(5000 + random.nextInt(5000));
        System.out.println("Finished " + this);
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("passed barrier " + this);
    }
}
