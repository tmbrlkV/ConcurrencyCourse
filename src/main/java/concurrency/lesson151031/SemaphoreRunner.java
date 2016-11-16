package concurrency.lesson151031;

import java.util.concurrent.Semaphore;

public class SemaphoreRunner implements Runnable {
    private Semaphore semaphore;

    public SemaphoreRunner(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        System.out.println("Runner " + this + " awaits");
        semaphore.acquireUninterruptibly();
        System.out.println("Runner " + this + " runs");
    }
}
