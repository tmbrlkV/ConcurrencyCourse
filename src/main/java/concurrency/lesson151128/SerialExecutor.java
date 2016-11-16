package concurrency.lesson151128;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {
    private Queue<Runnable> tasks = new ArrayDeque<>();
    private Executor executor;
    private Runnable active;

    public SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Runnable command) {
        synchronized (this) {
            tasks.offer(() -> {
                try {
                    command.run();
                } finally {
                    scheduleNext();
                }
            });
            if (active == null) {
                scheduleNext();
            }
        }
    }

    private void scheduleNext() {
        synchronized (this) {
            if ((active = tasks.poll()) != null) {
                executor.execute(active);
            }
        }
    }
}
