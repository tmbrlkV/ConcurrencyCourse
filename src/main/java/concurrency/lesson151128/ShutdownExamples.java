package concurrency.lesson151128;

import concurrency.utils.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownExamples {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(() -> {
            Utils.pause(3000);
            System.out.println("finished");
        });

        service.shutdown();

        service.execute(() -> System.out.println("exception"));

        System.out.println("the end");
    }
}
