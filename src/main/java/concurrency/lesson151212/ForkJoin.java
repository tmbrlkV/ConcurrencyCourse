package concurrency.lesson151212;

import concurrency.utils.Utils;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoin {
    private static class Task extends RecursiveAction {
        private int level;

        public Task(int level) {
            this.level = level;
        }

        @Override
        protected void compute() {
            System.out.println(this + " " + level);
            Utils.pause(1000);

            if (level == 0) {
                return;
            }
            Task subTask1 = new Task(level - 1);
            Task subTask2 = new Task(level - 1);

            invokeAll(Arrays.asList(subTask1, subTask2));
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        pool.invoke(new Task(5));
    }
}
