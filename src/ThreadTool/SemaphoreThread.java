package ThreadTool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by caesar on 16/3/19.
 */
public class SemaphoreThread {
    // PV操作
    private final Semaphore semaphore;

    public SemaphoreThread() {
        // 第二个参数是公平性和非公平性
        this.semaphore = new Semaphore(1,false);
    }
    public void printJob(Object object){
        try {
            semaphore.acquire();

            // 这个方法忽略中断,上面那个方法,如果有中断,会抛出异常 InterruptedException
            // semaphore.acquireUninterruptibly();

            //根据返回值true,false来处理,避免阻塞和等待信号量的释放
            //semaphore.tryAcquire();

            // 默认的是非公平的,公平的是根据等待时间最长的


            System.out.println("hello,world:"+ Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

    }

}

class Job implements Runnable {
    private SemaphoreThread semaphoreThread;

    public Job(SemaphoreThread semaphoreThread) {
        this.semaphoreThread = semaphoreThread;
    }

    @Override
    public void run() {
        System.out.println("i am working" + Thread.currentThread().getId());
        semaphoreThread.printJob(new Object());
    }


}
class SemaphoreMain{

    public static void main(String[] args) {

        SemaphoreThread semaphoreThread=new SemaphoreThread();


        Job[] jobs=new Job[10];
        Thread [] threads=new Thread[10];
        for (int i = 0; i <10 ; i++) {
            jobs[i]=new Job(semaphoreThread);
            threads[i]=new Thread(jobs[i]);
        }

        for (int i = 0; i <10 ; i++) {
            threads[i].start();
        }



    }
}