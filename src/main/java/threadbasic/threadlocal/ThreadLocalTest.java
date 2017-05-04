package threadbasic.threadlocal;

public class ThreadLocalTest {
    public static void main(String[] args) {
        MyThreadLocal threadLocal=new MyThreadLocal();
        Thread t1=new Thread(new ThreadLocalRunner(threadLocal),"t1");
        Thread t2=new Thread(new ThreadLocalRunner(threadLocal),"t2");
        Thread t3=new Thread(new ThreadLocalRunner(threadLocal),"t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
