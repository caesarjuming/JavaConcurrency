package ExecutorThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by caesar on 16/3/15.
 */

public class AnyExecutor {



    public static void main(String[] args) {

        ThreadPoolExecutor executorService= (ThreadPoolExecutor)Executors.newCachedThreadPool();

        List<MyCallableTask> callableList=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            callableList.add(new MyCallableTask("hello:"+i));
        }
        String s=null;
        try {
            //首个执行完毕的,就停止,只要有一个完成就赋值给s,如果有其他的抛出异常,忽略
            //如果是所有都抛出异常,则它抛出异常
            s=executorService.invokeAny(callableList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(s);
    }


}

class MyCallableTask implements Callable<String> {

    private String str;
    public MyCallableTask(String str) {
        this.str=str;
    }

    @Override
    public String call() throws Exception {
        return str;
    }
}

