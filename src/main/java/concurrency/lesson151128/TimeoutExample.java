package concurrency.lesson151128;

import concurrency.utils.Utils;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeoutExample {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        Future<?> future = service.submit(() -> {
            try {
                Utils.pause(100000);
            } finally {
                System.out.println("finished");
            }
        });

        service.schedule(() -> {
            if (!future.isDone()) {
                future.cancel(true);
            }
        }, 5, TimeUnit.SECONDS);

        future.isCancelled();
    }
}
