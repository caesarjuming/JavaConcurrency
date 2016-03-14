package ExecutorThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by caesar on 16/3/14.
 */
public class MyFixedExecutor {
    private ThreadPoolExecutor executor;

    public MyFixedExecutor() {
        this.executor =(ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }
}
