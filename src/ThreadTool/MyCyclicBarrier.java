package ThreadTool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by caesar on 16/3/21.
 */
public class MyCyclicBarrier {
    //允许两个或多个线程在某点进行同步
    public static void main(String[] args) {
        CountTask countTask=new CountTask();
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5,countTask);

        //当这5个线程都调用后,也就是调用await方法,那么countTask开始执行
        for (int i = 0; i <5 ; i++) {
            PerWorker p=new PerWorker(cyclicBarrier);
            Thread t=new Thread(p);
            t.start();
        }



    }
}

class CountTask implements Runnable{
    @Override
    public void run() {
        System.out.println("i am complete");
    }
}

class PerWorker implements Runnable{
    private CyclicBarrier cyclicBarrier;

    public PerWorker(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}