package ThreadTool;



import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by caesar on 16/3/21.
 */
public class MyPhaser {
    public static void main(String[] args) {
        // 3个线程阶段
        Phaser phaser=new Phaser(3);
        MyPhaserTask myPhaserTask=new MyPhaserTask(phaser);
        MyPhaserTask myPhaserTask2=new MyPhaserTask(phaser);
        MyPhaserTask myPhaserTask3=new MyPhaserTask(phaser);
        Thread t=new Thread(myPhaserTask);
        Thread t2=new Thread(myPhaserTask2);
        Thread t3=new Thread(myPhaserTask3);
        t2.start();
        t.start();
        t3.start();


    }
}


class MyPhaserTask implements Runnable{
    private Phaser phaser;

    public MyPhaserTask(Phaser phaser) {
        this.phaser = phaser;
    }

    private void Method1(){
        System.out.println("first");

    }
    private void Method2(){
        System.out.println("second");

    }
    private void Method3(){
        System.out.println("third");

    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        this.Method1();
        phaser.arriveAndAwaitAdvance();
        this.Method2();
        phaser.arriveAndAwaitAdvance();
        this.Method3();
        //此阶段已经结束,不再进行下个阶段
        //phaser.arriveAndDeregister();
    }
}


