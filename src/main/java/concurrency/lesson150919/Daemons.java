package concurrency.lesson150919;

import concurrency.utils.Utils;

public class Daemons {
    private static class Ticker implements Runnable {
        @Override
        public void run() {
            int x = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("x = " + x++);
                Utils.pause(1000);
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Ticker(), "Ticker");
        thread.setDaemon(true);
        thread.start();

        Utils.pause(5000);
    }
}
