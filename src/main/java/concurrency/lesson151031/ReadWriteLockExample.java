package concurrency.lesson151031;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private static class Dict {
        private ReadWriteLock lock = new ReentrantReadWriteLock(true);
        private Lock readLock = lock.readLock();
        private Lock writeLock = lock.writeLock();

        Map<String, String> data = new HashMap<>();

        public void put(String key, String value) {
            writeLock.lock();
            try {
                data.put(key, value);
            } finally {
                writeLock.unlock();
            }
        }

        public String get(String key) {
            readLock.lock();
            try {
                return data.get(key);
            } finally {
                readLock.unlock();
            }
        }
    }
}
