package ExecutorThread;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by caesar on 16/3/17.
 */
// 处理异常的要执行的线程
public class RejectedTaskController implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("i accepted "+executor.isShutdown());
        System.out.println("i accepted "+executor.isTerminated());
        System.out.println("i accepted "+executor.isTerminating());
    }
}

class M{
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        threadPoolExecutor.submit(new RejTask());
        threadPoolExecutor.setRejectedExecutionHandler(new RejectedTaskController());

        threadPoolExecutor.shutdown();
        threadPoolExecutor.submit(new RejTask());

    }
}

class RejTask implements Runnable{

    @Override
    public void run() {

    }
}
