package ExecutorThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by caesar on 16/3/14.
 */
public class MyThreadExecutor {

    public static void main(String[] args) {
        Server s = new Server();
        s.ExecuteTask(new Task());
        s.EndExecutor();
        // 当executor停止后,在启动线程会抛出RejectedExecutionException异常
        s.ExecuteTask(new Task());
    }

}

class Task implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class Server {

    private ThreadPoolExecutor executor;

    public Server() {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void ExecuteTask(Task task) {
        System.out.println("exe task");

        //execute执行runnable和callable
        executor.execute(task);

        System.out.println(executor.getPoolSize());
        System.out.println(executor.getMaximumPoolSize());
        System.out.println(executor.getActiveCount());
        System.out.println(executor.getCompletedTaskCount());

    }

    public void EndExecutor() {
        executor.shutdown();
    }

    public void EndNowExecutor() {
        //立刻结束,不等待
        executor.shutdownNow();
    }

    public boolean IsTerminate() {
        //如果调用shuntdown或者shutdownnow会返回true
        return executor.isTerminated();

    }

    public void awaitTermination() {
        try {
            // 阻塞线程运行完或者达到100秒
            executor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}