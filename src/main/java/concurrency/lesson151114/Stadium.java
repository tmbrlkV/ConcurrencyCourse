package concurrency.lesson151114;

public class Stadium {
    public static void main(String[] args) {
        Barrier barrier = new Barrier(4, () -> System.out.println("Hurry up!"));

        new Thread(new Stayer(barrier)).start();
        new Thread(new Stayer(barrier)).start();
        new Thread(new Stayer(barrier)).start();
        new Thread(new Stayer(barrier)).start();

        barrier.waitUntilDone();
    }
}
