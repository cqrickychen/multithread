package threadbasic;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MutliThread {
    public static void main(String[] args) {
        ThreadMXBean m = ManagementFactory.getThreadMXBean();
        ThreadInfo[] infos = m.dumpAllThreads(false, false);
        for (ThreadInfo info:infos){
            System.out.println(info.getThreadId()+":"+info.getThreadName()+"     "+info.getThreadState());
        }
    }
}
