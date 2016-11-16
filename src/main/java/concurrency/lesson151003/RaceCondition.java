package concurrency.lesson151003;

import concurrency.utils.Utils;

public class RaceCondition {
    private static int count = 0;

    private static class Counter implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                inc();
            }
        }

        private void inc() {
            synchronized (Counter.class) {
                int tmp = count;
                tmp++;
                count = tmp;
            }
        }
    }

    public static void main(String[] args) {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        Thread thread1 = new Thread(counter1);
        thread1.start();

        Thread thread2 = new Thread(counter2);
        thread2.start();

        Utils.pause(5000);
        thread1.interrupt();
        thread2.interrupt();

        System.out.println("Counter = " + count);
    }
}
