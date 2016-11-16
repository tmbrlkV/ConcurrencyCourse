package concurrency.lesson151031;

import concurrency.utils.Utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseRunner {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(3);

        executorService.execute(new Runner(latch));
        executorService.execute(new Runner(latch));
        executorService.execute(new Runner(latch));
        executorService.execute(new Runner(latch));

        Utils.pause(1000);
        System.out.println("Ready...");
        latch.countDown();
        System.out.println("Steady...");
        latch.countDown();
        System.out.println("Go!!!");
        latch.countDown();
    }
}
