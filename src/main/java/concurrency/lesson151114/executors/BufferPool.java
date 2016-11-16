package concurrency.lesson151114.executors;

import java.util.concurrent.*;

public class BufferPool {
    public static void main(String[] args) {
        BlockingQueue<Callable<Integer>> queue = new ArrayBlockingQueue<>(10);

        class Task implements Callable<Integer> {
            @Override
            public Integer call() throws Exception {
                try {
                    int x = 0;
                    for (int i = 0; i < 1_000_000_000; ++i) {
                        x += i;
                    }
                    return x;
                } finally {
                    queue.put(this);
                }
            }
        }

        for (int i = 0; i < 10; ++i) {
            try {
                queue.put(new Task());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ExecutorService executorService = Executors.newCachedThreadPool();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                executorService.submit(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}