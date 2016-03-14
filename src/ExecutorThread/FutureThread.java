package ExecutorThread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by caesar on 16/3/14.
 */
public class FutureThread {
    public static void main(String[] args) {
        ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        List<Future<String>> list=new LinkedList<>();
        for (int i = 0; i <10 ; i++) {
            Future<String> future=executor.submit(new CallableTask());
            list.add(future);
        }
        for (Future<String> f:list) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}


class CallableTask implements Callable<String>{

    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName()+",hello,world";
    }
}
