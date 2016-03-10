package SimplestThread;

import java.util.concurrent.TimeUnit;

/**
 * Created by caesar on 16/3/10.
 */
public class JoinThread extends Thread {
    @Override
    public void run() {

        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getId()+":end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.run();
    }


    public static void main(String[] args) {
        JoinThread[] jt=new JoinThread[10];
        for (int i = 0; i <10 ; i++) {
            JoinThread j=new JoinThread();
            jt[i]=j;
            j.start();

        }

        for (int i = 0; i <jt.length ; i++) {
            try {
                jt[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }

}
