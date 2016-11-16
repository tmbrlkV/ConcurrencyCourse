package concurrency.lesson151128;

import concurrency.utils.Utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample1 {
    public static void main(String[] args) {
        System.out.println("start");
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(() -> {
            Utils.pause(3000);
            return "Hello World";
        });

        System.out.println("wait");
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
