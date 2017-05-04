package threadbasic;

import java.util.concurrent.TimeUnit;

/**
 * Created by zcg on 2017/4/30.
 */
public class Interrupted {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new SleepRunner(), "SleepThread");
        thread1.setDaemon(true);
        Thread thread2 = new Thread(new BusyRunner(), "BusyThread");
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
        thread2.interrupt();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SleepThread interrupted is "+thread1.isInterrupted());
        System.out.println("BusyThread interrupted is "+thread1.isInterrupted());
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            for (; ; ) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true){}
        }
    }
}
