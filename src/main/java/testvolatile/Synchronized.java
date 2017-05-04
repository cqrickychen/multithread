package testvolatile;

/**
 * Created by zcg on 2017/4/25.
 */
public class Synchronized {
    public synchronized void m() {
    }

    public void n() {
        synchronized (this) {
        }
    }
}
