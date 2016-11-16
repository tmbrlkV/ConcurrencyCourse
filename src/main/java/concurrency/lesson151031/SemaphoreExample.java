package concurrency.lesson151031;

import concurrency.utils.Utils;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static final Random random = new Random();

    private static class Skater implements Runnable {
        private Semaphore semaphore;

        public Skater(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            System.out.println("Start");
            Utils.pause(random.nextInt(5000) + 5000);
            System.out.println("Finish");
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        ExecutorService service = Executors.newCachedThreadPool();

        while (!Thread.currentThread().isInterrupted()) {
            semaphore.acquireUninterruptibly();
            service.execute(new Skater(semaphore));
        }
    }
}
