package ExecutorThread;

import java.util.concurrent.*;

/**
 * Created by caesar on 16/3/17.
 */
public class DoneExecutor {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        DoneTask doneTask=new DoneTask();

        MyFutureTask[] myFutureTask=new MyFutureTask[5];

        for (int i=0;i<5;i++){
            myFutureTask[i]=new MyFutureTask(doneTask);
            threadPoolExecutor.submit(doneTask);
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=0;i<3;i++){
            myFutureTask[i].cancel(true);
        }

        for(int i=0;i<5;i++){
            if(!myFutureTask[i].isCancelled()){
                try {
                    System.out.println(myFutureTask[i].get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        threadPoolExecutor.shutdown();
    }
}


class DoneTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        return null;
    }
}

class MyFutureTask<String> extends FutureTask<String>{

    public MyFutureTask(Callable<String> callable) {
        super(callable);
    }

    // 在任务执行后调用的方法
    @Override
    protected void done() {
        if(isCancelled()){
            System.out.println("I am cancelled");
        }else {
            System.out.println("I am finished");
        }

    }
}