package concurrency.lesson151017;

import java.util.LinkedList;
import java.util.Queue;

public class BoudedBlockingQueue<T> {
    private final Queue<T> items = new LinkedList<>();
    private int capacity;

    public BoudedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T item) {
        synchronized (items) {
            while (items.size() == capacity) {
                try {
                    items.wait();
                } catch (InterruptedException e) {
                    items.notify();
                    e.printStackTrace();
                }
            }
            items.offer(item);
            items.notify();
        }
    }

    public T get() {
        synchronized (items) {
            while (items.isEmpty()) {
                try {
                    items.wait();
                } catch (InterruptedException e) {
                    items.notify();
                    e.printStackTrace();
                }
            }
            T item = items.poll();
            items.notify();
            return item;
        }
    }
}
