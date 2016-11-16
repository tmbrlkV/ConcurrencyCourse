package concurrency.lesson151031;

import concurrency.utils.Utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample1 {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        class Task implements Runnable {
            @Override
            public void run() {
                System.out.println("start lock!");
//                try {
//                    lock.lockInterruptibly();
//                } catch (InterruptedException e) {
//                    System.err.println("interrupted!");
//                    return;
//                }
//                try {
//                    boolean success = lock.tryLock(4, TimeUnit.SECONDS);
//                    if (!success) {
//                        System.out.println("Timeout!");
//                        return;
//                    }
//                } catch (InterruptedException e) {
//                    System.err.println("interrupted!");
//                    return;
//                }
                boolean success = lock.tryLock();
                if (!success) {
                    System.out.println("Timeout!");
                    return;
                }
                try {
                    System.out.println("got lock!");
                } finally {
                    lock.unlock();
                    System.out.println("unlocked!");
                }
            }
        }

        lock.lock();
        Thread thread = new Thread(new Task());
        thread.start();
        Utils.pause(5000);
        thread.interrupt();
    }
}
