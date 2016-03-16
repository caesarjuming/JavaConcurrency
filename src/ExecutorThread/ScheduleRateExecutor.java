package ExecutorThread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by caesar on 16/3/16.
 */

public class ScheduleRateExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService= (ScheduledExecutorService) Executors.newScheduledThreadPool(1);
        class myTask implements Runnable {
            @Override
            public void run() {
                System.out.println("hello,world");
            }
        }

        //* @param initialDelay the time to delay first execution
        //* @param period the period between successive executions
        //* @param unit the time unit of the initialDelay and period parameters


        // runnable ,首次执行线程等待的时间,每个线程的间隔时间
        //scheduledExecutorService.scheduleAtFixedRate(new myTask(),10,1, TimeUnit.SECONDS);
        //scheduledExecutorService.shutdown();

        // runnable ,首次执行线程等待的时间,和上面的区别是,等上一个线程执行完毕,每个线程的间隔时间
        scheduledExecutorService.scheduleWithFixedDelay(new myTask(),10,1,TimeUnit.SECONDS);






    }
}
