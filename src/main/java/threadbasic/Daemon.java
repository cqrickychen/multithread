package threadbasic;

import java.util.concurrent.TimeUnit;

public class Daemon {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner01");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            System.out.println("Daemon start");
            try {
                System.out.println("Daemon sleep");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("Daemon wake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Daemon finally");
            }
            System.out.println("Daemon over");
        }
    }
}
