package concurrency.lesson151114;


import java.util.concurrent.Semaphore;

public class Barrier {
    private int participants;
    private Runnable finalTask;
    private int finished;
    private Semaphore finishSemaphore = new Semaphore(0);
    private Semaphore lock = new Semaphore(1);
    private Semaphore allDone = new Semaphore(0);

    public Barrier(int participants, Runnable finalTask) {
        this.participants = participants;
        this.finalTask = finalTask;
    }

    public void await() {
        lock.acquireUninterruptibly();
        try {
            if (++finished == participants) {
                allDone.release();
            }
        } finally {
            lock.release();
        }
        finishSemaphore.acquireUninterruptibly();
    }

    public void waitUntilDone() {
        allDone.acquireUninterruptibly();
        try {
            finalTask.run();
        } finally {
            finishSemaphore.release(participants);
        }
    }
}
