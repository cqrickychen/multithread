package cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    public static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(0, 0);

    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        int stamp = money.getStamp();
                        int m = money.getReference();
                        boolean success;
                        if (j % 2 == 0) {
                            success = money.compareAndSet(m, m + 10, stamp, stamp + 1);
                            if (!success){
                                System.out.println("recharge fail");
                            }
                        } else {
                            if (m<=0){
                                System.out.println("money is zero");
                                continue;
                            }
                            success = money.compareAndSet(m, m - 10, stamp, stamp + 1);
                            if (!success){
                                System.out.println("consume fail");
                            }
                        }

                    }
                }
            };
            threadList.add(new Thread(runnable));
        }

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

        System.out.println("money:"+money.getReference());
        System.out.println("stamp:"+money.getStamp());
    }
}
