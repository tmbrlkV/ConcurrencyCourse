package concurrency.lesson151031;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue<T> {
    private Queue<T> items = new LinkedList<>();
    private int capacity;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T item) {
        lock.lock();
        try {
            if (capacity == items.size()) {
                notFull.awaitUninterruptibly();
            }
            items.offer(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        lock.lock();
        try {
            if (items.isEmpty()) {
                notEmpty.awaitUninterruptibly();
            }
            notFull.signal();
            return items.poll();
        } finally {
            lock.unlock();
        }
    }
}