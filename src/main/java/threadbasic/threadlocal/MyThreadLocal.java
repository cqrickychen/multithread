package threadbasic.threadlocal;

public class MyThreadLocal {

    private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public Integer count() {
        Integer t = threadLocal.get();
        if (t != null) {
            threadLocal.set(t + 1);
        } else {
            threadLocal.set(0);
        }
        return threadLocal.get();
    }
}
