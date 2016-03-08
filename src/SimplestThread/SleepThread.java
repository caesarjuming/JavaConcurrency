package SimplestThread;

import java.util.concurrent.TimeUnit;

/**
 * Created by caesar on 16/3/8.
 */
public class SleepThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <Integer.MAX_VALUE ; i++) {
            System.out.println(i);
            try {
                //sleep让出时间片
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Thread thread=new Thread(new SleepThread());
        thread.start();
    }
}
