package ExecutorThread;

import java.util.concurrent.*;

/**
 * Created by caesar on 16/3/15.
 */
public class ScheduleThreadExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService scheduleThreadExecutor=  Executors.newScheduledThreadPool(1);
        class InTask implements Callable<String>{

            @Override
            public String call() throws Exception {
                System.out.println("hello,world");
                return "hello,world";
            }
        }

        // 2秒后执行这5个线程,

        for (int i = 0; i <5 ; i++) {
            //每个线程相隔1秒
            scheduleThreadExecutor.schedule(new InTask(),2+i, TimeUnit.SECONDS);
        }

        // 2 秒之后
        scheduleThreadExecutor.shutdown();

        try {
            scheduleThreadExecutor.awaitTermination(3,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }






    }
}
