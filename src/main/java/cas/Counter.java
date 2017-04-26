package cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger count1 = new AtomicInteger(0);
    private int count2 = 0;

    public static void main(String[] args) {
        final Counter counter = new Counter();
        List<Thread> threadList = new ArrayList<Thread>(100);
        for (int i = 0; i < 100; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        counter.countAtomic();
                        counter.count();
                    }
                }
            };
            threadList.add(new Thread(runnable));
        }

        long start = System.currentTimeMillis();
        for (Thread t : threadList) {
            t.start();
        }

        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("time:" + (System.currentTimeMillis() - start));
        System.out.println("count1:" + counter.count1.get());
        System.out.println("count2:" + counter.count2);
    }

    public void countAtomic() {
        for (; ; ) {
            int t = count1.get();
            boolean success = count1.compareAndSet(t, t + 1);
            if (success) {
                break;
            } else {
                System.out.println("cas fail!");
            }
        }
    }

    public void count() {
        count2++;
    }
}
