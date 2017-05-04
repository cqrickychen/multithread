package threadbasic;

import java.util.concurrent.TimeUnit;

public class Shutdown {
    public static void main(String[] args) {
        Runner one = new Runner();
        Runner two = new Runner();
        Thread t1 = new Thread(one, "t1");
        Thread t2 = new Thread(two, "t2");
        t1.start();
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        two.cannel();
    }

    static class Runner implements Runnable {

        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on & !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("count=" + i);
        }

        public void cannel() {
            on = false;
        }
    }
}
