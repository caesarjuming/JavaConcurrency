package ThreadTool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by caesar on 16/3/20.
 */
public class MyCountDownLatch {
    public static void main(String[] args) {
        VideoConference videoConference=new VideoConference(5);
        Thread t=new Thread(videoConference);
        t.start();


        for (int i=0;i<10;i++){
            Participant p=new Participant(videoConference,"participant"+i);
            Thread tr=new Thread(p);
            tr.start();
        }
    }
}

class VideoConference implements Runnable{
    private final CountDownLatch countDownLatch;

    public VideoConference(int i){
        countDownLatch=new CountDownLatch(i);
    }

    @Override
    public void run() {
        try {
            // 等待其他线程执行完毕,countDownLatch的值为0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all in here");

    }

    public void arrive(String name){
        System.out.println(name+",i am here");
        // 减1
        countDownLatch.countDown();
        System.out.println("counts was "+countDownLatch.getCount());
    }
}

class Participant implements Runnable{

    private VideoConference videoConference;
    private String name;

    public Participant(VideoConference videoConference,String name) {
        this.videoConference = videoConference;
        this.name=name;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 发生减1
        videoConference.arrive(name);

    }
}