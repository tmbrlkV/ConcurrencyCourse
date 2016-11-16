package concurrency.lesson151212;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinFibonacci {
    private static class Fibonacci extends RecursiveTask<Integer> {
        private final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }

            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 1);
            return f2.compute() + f1.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(20);

        long start = System.nanoTime();

        Integer result = pool.invoke(new Fibonacci(25));

        long stop = System.nanoTime();

        System.out.println("Result: " + result);
        System.out.println("Elapsed: " + (stop - start) / 1000);
    }
}
