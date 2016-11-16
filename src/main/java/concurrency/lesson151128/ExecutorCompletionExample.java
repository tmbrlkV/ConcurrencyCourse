package concurrency.lesson151128;

import concurrency.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorCompletionExample {
    private static final Random random = new Random();

    private static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Utils.pause(5000 + random.nextInt(5000));
            return random.nextInt(100000);
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(service);

        for (int i = 0; i < 20; ++i) {
            completionService.submit(new Task());
        }

        int sum = 0;
        for (int i = 0; i < 20; ++i) {
            try {
                Integer data = completionService.take().get();
                sum += data;
                System.out.println(data);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sum = " + sum);
    }
}
