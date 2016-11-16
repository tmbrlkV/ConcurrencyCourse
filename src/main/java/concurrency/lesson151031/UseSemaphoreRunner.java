package concurrency.lesson151031;

import concurrency.utils.Utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class UseSemaphoreRunner {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(0);

        executorService.execute(new SemaphoreRunner(semaphore));
        executorService.execute(new SemaphoreRunner(semaphore));
        executorService.execute(new SemaphoreRunner(semaphore));
        executorService.execute(new SemaphoreRunner(semaphore));

        Utils.pause(1000);
        System.out.println("Ready...");
        System.out.println("Steady...");
        System.out.println("Go!!!");
        semaphore.release(4);
    }
}
