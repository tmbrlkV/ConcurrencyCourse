package concurrency.lesson151017;

import concurrency.utils.Utils;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionsWithAtomics {
    private static AtomicInteger count = new AtomicInteger(0);


    public static void main(String[] args) {
        Runnable runnable = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                int value = count.incrementAndGet();
                System.out.println(value);
                Utils.pause(1000);
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
