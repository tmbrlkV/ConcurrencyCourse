package concurrency.lesson151114.executors;

import concurrency.utils.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            System.out.println("Hello from " + Thread.currentThread());
            Utils.pause(4000);
        });
        System.out.println("Shutting down");
        executorService.shutdown();
        System.out.println("Finished main");
    }
}
