package reorder;

/**
 * Created by zcg on 2017/4/28.
 */
public class MemoryReorder2 {
    int a = 0;
    boolean flag = false;

    public void writer() {
        a = 1;                   //1
        flag = true;             //2
    }

    public void reader() {
        int temp = a * a;
        if (flag) {                //3
            int i = temp;       //4
            System.out.println("i=" + i);
        }
    }

    public static void main(String[] args) {
        MemoryReorder2 m = new MemoryReorder2();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                m.writer();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
               m.reader();
            }
        });
        t1.start();
        t2.start();

        System.out.println("a=" + m.a);
        System.out.println("flag=" + m.flag);
    }
}
