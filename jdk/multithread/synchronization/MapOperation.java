package jdk.multithread.synchronization;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MapOperation {

    private Lock lock;
    private Map<Integer, Integer> map;

    public MapOperation(Map<Integer, Integer> map) {
        lock = new ReentrantLock();
        this.map = map;
    }

    public void setMap(int startValue, String threadName)
            throws InterruptedException {
        for (int i=startValue; i<startValue+5; i++) {
            this.map.put(1, i);
            Thread.sleep(10);
            System.out.println(threadName + ": " + this.map.get(1));
        }
    }

    public synchronized void setMapSync1(int startValue, String threadName)
            throws InterruptedException {
        setMap(startValue, threadName);
    }

    public void setMapSync2(int startValue, String threadName)
            throws InterruptedException {
        synchronized (this) {
            setMap(startValue, threadName);
        }
    }

    public void setMapLock(int startValue, String threadName)
            throws InterruptedException {
        lock.lock();
        setMap(startValue, threadName);
        lock.unlock();
    }

}
