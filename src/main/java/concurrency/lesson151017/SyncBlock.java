package concurrency.lesson151017;

import concurrency.utils.Utils;

public class SyncBlock {
    public static void main(String[] args) {
        final Object mutex = new Object();
        Thread thread = new Thread(() -> {
            System.out.println("started");
            synchronized (mutex) {
                System.out.println("Ha ha! Starvation!");
            }
        });

        synchronized (mutex) {
            thread.start();
            Utils.pause(1000);
            while (!Thread.currentThread().isInterrupted()) {
                Utils.pause(1000);
                System.out.println("ho ho it is livelock");
                thread.interrupt();
            }
        }
    }
}
