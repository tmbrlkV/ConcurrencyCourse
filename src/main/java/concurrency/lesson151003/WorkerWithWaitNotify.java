package concurrency.lesson151003;

import concurrency.utils.Utils;

import java.util.LinkedList;
import java.util.Queue;

public class WorkerWithWaitNotify {
    private final Queue<Runnable> tasks = new LinkedList<>();

    private class Runner implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable task;
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.poll();
                }
                task.run();
            }
        }
    }


    private WorkerWithWaitNotify() {
        new Thread(new Runner()).start();
    }

    private void execute(Runnable task) {
        synchronized (tasks) {
            tasks.offer(task);
            tasks.notify();
        }
    }

    public static void main(String[] args) {
        WorkerWithWaitNotify worker = new WorkerWithWaitNotify();
        Utils.pause(3000);
        worker.execute(() -> System.out.println("Hello!"));
    }
}
