package jdk.multithread.synchronization;

public class SubThread implements Runnable {

    private String threadName;
    private MapOperation mapOp;
    private int startValue;
    private int methodNo;

    public SubThread(String threadName, MapOperation mapOp, int startValue, int methodNo) {
        this.threadName = threadName;
        this.mapOp = mapOp;
        this.startValue = startValue;
        this.methodNo = methodNo;
    }

    @Override
    public void run() {
        try {
            switch (methodNo) {
                case 0:
                    mapOp.setMap(startValue, threadName);
                    break;
                case 1:
                    mapOp.setMapSync1(startValue, threadName);
                    break;
                case 2:
                    mapOp.setMapSync2(startValue, threadName);
                    break;
                case 3:
                    mapOp.setMapLock(startValue, threadName);
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
