package threadbasic;

import java.util.ArrayList;
import java.util.List;

public class ThreadPriority {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;


    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job);
            thread.setPriority(priority);
            thread.setName("Thread:" + i);
            thread.start();
        }
        notStart = false;
        Thread.sleep(10000);
        notEnd = false;
        for (Job j : jobs) {
            System.out.println("priority=" + j.getPriority() + " jobCount=" + j.getJobCount());
        }
    }

    static class Job implements Runnable {

        private int priority;

        private int jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }
            while (notEnd) {
                Thread.yield();
                jobCount++;
            }
        }

        public int getPriority() {
            return priority;
        }

        public int getJobCount() {
            return jobCount;
        }
    }
}

