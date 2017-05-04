package threadbasic.threadlocal;

public class ThreadLocalRunner implements Runnable {

    private MyThreadLocal threadLocal;

    public ThreadLocalRunner(MyThreadLocal threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public void run() {
        for (int i=0;i<3;i++) {
            System.out.println(Thread.currentThread().getName() + " count " + threadLocal.count());
        }
    }
}
