package concurrency.lesson150919;

import concurrency.utils.Utils;

public class FirstThread {
    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hi there!");
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                if (++i % 100_000_000 == 0) {
                    System.out.println(i);
                }
            }
        }
    }

    private static class RunnableThread implements Runnable {
        @Override
        public void run() {
            System.out.println("Hi!");
            Utils.pause(10000);
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        new Thread().start();
        new MyThread().start();
        new Thread(new RunnableThread()).start();
    }
}
