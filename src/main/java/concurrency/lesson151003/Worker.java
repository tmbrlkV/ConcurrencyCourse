package concurrency.lesson151003;

import concurrency.utils.Utils;

public class Worker {
    private Runnable task;

    private class Runner implements Runnable {
        @Override
        public void run() {
            while(hasNextTask()) {
                Runnable task = getNextTask();
                task.run();
            }
        }

        private Runnable getNextTask() {
            Runnable tmp = task;
            task = null;
            return tmp;
        }

        private boolean hasNextTask() {
            while (task == null) {
                Utils.pause(100);
            }
            return true;
        }
    }


    private Worker() {
        new Thread(new Runner()).start();
    }

    private void execute(Runnable task) {
        this.task = task;
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        Utils.pause(3000);
        worker.execute(() -> System.out.println("Hello"));
    }
}
