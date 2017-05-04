package reorder;

/**
 * Created by zcg on 2017/4/28.
 */
public class MemoryReorder {
    public int a;
    public int b;
    public int x;
    public int y;

    public static void main(String[] args) {
        MemoryReorder m = new MemoryReorder();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                m.a = 1;
                m.x = m.b;
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                m.b = 2;
                m.y = m.a;
            }
        });
        t1.start();
        t2.start();
        System.out.println("a="+m.a);
        System.out.println("b="+m.b);
        System.out.println("x="+m.x);
        System.out.println("y="+m.y);
    }
}
