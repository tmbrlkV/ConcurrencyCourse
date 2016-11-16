package concurrency.lesson151114.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CachedThreadPool {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);

        Callable<Integer> task = () -> {
            try {
                int x = 0;
                for (int i = 0; i < 1_000_000_000; ++i) {
                    x += i;
                }
                return x;
            } finally {
                semaphore.release();
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();

        while (!Thread.currentThread().isInterrupted()) {
            semaphore.acquireUninterruptibly();
            executorService.submit(task);
        }
    }
}