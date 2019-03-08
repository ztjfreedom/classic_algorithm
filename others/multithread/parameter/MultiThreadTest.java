package others.multithread.parameter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiThreadTest {

    private Map<Integer, String> classMap = new ConcurrentHashMap<>();

    public Map<Integer, String> getClassMap() {
        return classMap;
    }

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "Tom");
        map.put(2, "Jim");

        // Extend Thread
        ExtThread extInstance = new ExtThread();
        extInstance.start();

        // Implement Runnable with constructor
        ImplRunnable implInstance1 = new ImplRunnable(map);
        Thread thread1 = new Thread(implInstance1);
        thread1.start();
        Thread.sleep(500);
        print(map);

        // Implement Runnable with recall method
        MultiThreadTest mainObj = new MultiThreadTest();
        ImplRunnable implInstance2 = new ImplRunnable(mainObj);
        Thread thread2 = new Thread(implInstance2);
        mainObj.getClassMap().put(1, "Java");
        mainObj.getClassMap().put(2, "Python");
        thread2.start();
        Thread.sleep(500);
        print(mainObj.getClassMap());
    }

    public static void print(Map<Integer, String> map) {
        System.out.println("Main:");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
