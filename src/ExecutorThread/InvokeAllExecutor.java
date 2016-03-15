package ExecutorThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by caesar on 16/3/15.
 */
public class InvokeAllExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor) Executors.newCachedThreadPool();

        List<MyTask> list= new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add(new MyTask("mytask:"+i));
        }
        List<Future<String>> futurelist=null;
        try {
             futurelist=threadPoolExecutor.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future<String> f:futurelist) {
            //都是true 因为,invokeAll会等待所有线程执行完毕
            System.out.println(f.isDone());
        }

    }
}


class MyTask implements Callable<String>{

    private String str;

    public MyTask(String str) {
        this.str = str;
    }

    @Override
    public String call() throws Exception {
        return str;
    }
}