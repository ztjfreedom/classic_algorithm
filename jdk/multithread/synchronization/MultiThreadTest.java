package jdk.multithread.synchronization;

import java.util.HashMap;
import java.util.Map;

public class MultiThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Integer> map = new HashMap<>();
        MapOperation mapOp = new MapOperation(map);

        for (int i=0; i<=3; i++) {
            startMultiThread(mapOp, i);
            System.out.println();
            Thread.sleep(1000);
        }

    }

    public static void startMultiThread(MapOperation mapOp, int methodNo) {
        SubThread instance1 = new SubThread("thread1", mapOp, 10, methodNo);
        SubThread instance2 = new SubThread("thread2", mapOp, 100, methodNo);
        SubThread instance3 = new SubThread("thread3", mapOp, 1000, methodNo);
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        Thread thread3 = new Thread(instance3);
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println();
    }

}
