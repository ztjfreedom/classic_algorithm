package jdk.multithread.parameter;

import java.util.Map;

// Implements Runnable
public class ImplRunnable implements Runnable {

    private Map<Integer, String> map;
    private MultiThreadTest mainObj;

    // solution 2 to get parameter from main thread
    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    // solution 1 to get parameter from main thread
    public ImplRunnable(Map<Integer, String> map) {
        this.map = map;
    }

    // solution 3 to get parameter from main thread (recall method: get the parameter when you need)
    public ImplRunnable(MultiThreadTest mainObj) {
        this.mainObj = mainObj;
    }

    @Override
    public void run() {
        System.out.println("SubThread:");
        if (this.map == null) {
            this.map = this.mainObj.getClassMap();
        }
        for (Map.Entry<Integer, String> entry : this.map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        this.map.remove(1);
    }

}
