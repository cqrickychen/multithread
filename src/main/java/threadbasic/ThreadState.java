package threadbasic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ThreadState {

    public static void main(String[] args) {
        Thread t1=new Thread(new TimeWaiting(),"TimeWaiting");
        Thread t2=new Thread(new Waiting(),"Waiting");
        Thread t3=new Thread(new Blocked(),"Blocked1");
        Thread t4=new Thread(new Blocked(),"Blocked2");
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    static class TimeWaiting implements Runnable {
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

    static class Waiting implements Runnable {
        @Override
        public void run() {
            for (; ; ) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable {
        @Override
        public void run() {
            for (;;){
                synchronized(Blocked.class){
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}