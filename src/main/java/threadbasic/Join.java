package threadbasic;

import java.util.concurrent.TimeUnit;

public class Join {
    public static void main(String[] args) {
        Thread p=Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Runner runner=new Runner(p);
            p=new Thread(runner,"thread-"+i);
            p.start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" is terminate");
    }

    static class Runner implements Runnable {

        private Thread thread;

        public Runner(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" is terminate");
        }
    }
}
