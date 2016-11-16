package concurrency.lesson151017;

import concurrency.utils.Utils;

import java.util.Vector;

/**
 * Created by master on 11/13/16.
 */
public class EvilSync {
    public static void main(String[] args) {
        Vector<String> v = new Vector<>();

        Thread thread = new Thread(() -> {
            System.out.println("start");
            v.add("one");
            System.out.println(v);
            v.add("two");
            System.out.println(v);
            v.add("three");
            System.out.println(v);
        });
        synchronized (v) {
            thread.start();
            while (!Thread.currentThread().isInterrupted()) {
                Utils.pause(1000);
                System.out.println("ho-ho");
            }
        }
    }
}
