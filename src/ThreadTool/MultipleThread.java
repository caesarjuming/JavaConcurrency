package ThreadTool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by caesar on 16/3/20.
 */
public class MultipleThread {
    // 一个资源的多个副本

    private boolean []freePrint;
    private Lock lockPrinters;
    private Semaphore semaphore;

    MultipleThread(){
        freePrint=new boolean[3];
        semaphore=new Semaphore(3);
        lockPrinters=new ReentrantLock();

        for (int i = 0; i <freePrint.length ; i++) {
            freePrint[i]=true;
        }

    }

    public void printJob(){
        try {
            semaphore.acquire();
            int p=getPrinter();
            TimeUnit.SECONDS.sleep(2);
            System.out.println("i am printing:"+Thread.currentThread().getId());
            freePrint[p]=true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    //获取打印机
    private int getPrinter(){
        int ret=-1;
        lockPrinters.lock();
        for(int i=0;i<freePrint.length;i++){
            if(freePrint[i]){
                ret=i;
                freePrint[i]=false;
                break;
            }
        }
        lockPrinters.unlock();
        return ret;
    }
}

class MultipleJob implements Runnable {
    private MultipleThread multipleThread;

    public MultipleJob(MultipleThread multipleThread) {
        this.multipleThread = multipleThread;
    }

    @Override
    public void run() {
        System.out.println("i am working" + Thread.currentThread().getId());
        multipleThread.printJob();
    }


}

class MultipleMain{

    public static void main(String[] args) {
        MultipleThread multipleThread=new MultipleThread();
        MultipleJob[] jobs=new MultipleJob[10];
        Thread [] threads=new Thread[10];
        for (int i = 0; i <10 ; i++) {
            jobs[i]=new MultipleJob(multipleThread);
            threads[i]=new Thread(jobs[i]);
        }

        for (int i = 0; i <10 ; i++) {
            threads[i].start();
        }
    }
}