package concurrency.lesson151017;

import concurrency.utils.Utils;

public class SingleThreadExecutor {
    private BlockingQueue<Runnable> queue = new BlockingQueue<>();
    private class Runner implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                queue.get().run();
            }
        }
    }

    private SingleThreadExecutor() {
        new Thread(new Runner()).start();
    }

    private void execute(Runnable task) {
        queue.put(task);
    }

    public static void main(String[] args) {
        SingleThreadExecutor worker = new SingleThreadExecutor();
        Utils.pause(3000);
        worker.execute(() -> System.out.println("Hello!"));
    }
}