package concurrency.lesson151017;

import concurrency.utils.Utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncBlockLock {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        Thread thread = new Thread(() -> {
            System.out.println("started");
            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("ha-ha!");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                System.err.println("interrupted!");
            }
        });

        lock.lock();
        try {
            thread.start();
            Utils.pause(1000);
            while (!Thread.currentThread().isInterrupted()) {
                Utils.pause(1000);
                System.out.println("ho-ho");
                thread.interrupt();
            }
        } finally {
            lock.unlock();
        }
    }
}
