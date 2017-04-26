package testvolatile;

/**
 * Created by zcg on 2017/4/25.
 */
public class TestVolatile {
    private volatile int a;

    public TestVolatile(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
