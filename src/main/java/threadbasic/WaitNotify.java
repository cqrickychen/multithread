package threadbasic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        public void run() {
            //加锁
            synchronized (lock) {
                //当条件不满足的时候，进入WAITTING状态，同时释放lock锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true,wait at " +
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //条件满足,完成工作
                System.out.println(Thread.currentThread() + " flag is false,finish at " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        public void run() {
            //加锁,拥有lock的monitor
            synchronized (lock) {
                //获取lock的锁，然后进行通知，通知不会释放lock锁
                //直到发出通知的线程执行完毕释放了lock锁，WaitThread线程才能从wait方法返回
                System.out.println(Thread.currentThread() + " hold lock,notify at " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again,sleep at " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}