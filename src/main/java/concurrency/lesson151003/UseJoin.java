package concurrency.lesson151003;

import concurrency.utils.Utils;

public class UseJoin {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("Start");
            Utils.pause(3000);
            System.out.println("Stop");
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Exit");
    }
}
