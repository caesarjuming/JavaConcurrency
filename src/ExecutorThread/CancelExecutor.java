package ExecutorThread;

import java.util.concurrent.*;

/**
 * Created by caesar on 16/3/17.
 */
public class CancelExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        CancelTask task=new CancelTask();
        Future<String> future=threadPoolExecutor.submit(task);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        future.cancel(true);

        try {
            future.get();
        } catch (InterruptedException e) {
            //取消后,get会产生异常中断
            e.printStackTrace();
        } catch (ExecutionException e) {
            //取消后,get会产生异常中断
            e.printStackTrace();
        }


        System.out.println("did'nt work");
        threadPoolExecutor.shutdown();

    }
}
class CancelTask implements Callable<String> {


    @Override
    public String call() throws Exception {
        int i=0;
        while (true){
            System.out.println("i am working:"+i);
            TimeUnit.SECONDS.sleep(2);
            i++;
        }
    }
}