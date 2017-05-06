package collection;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueueTest {
    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(3);

    public static void main(String[] args) {
        Thread p=new Thread(new Producer());
        Thread c=new Thread(new Consumer());
        p.start();
        c.start();
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    queue.put("apple" + i);
                    System.out.println("Producer:" + "apple" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    String t = queue.take();
                    System.out.println("Consumer:" + t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
