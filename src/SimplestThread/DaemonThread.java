package SimplestThread;

/**
 * Created by caesar on 16/3/10.
 */
public class DaemonThread implements Runnable{
    @Override
    public void run() {

    }

    //守护进程

    public static void main(String[] args) {
        DaemonThread daemonThread=new DaemonThread();
        Thread t=new Thread(daemonThread);
        //must set it before start method
        t.setDaemon(true);
        t.start();
    }


}
