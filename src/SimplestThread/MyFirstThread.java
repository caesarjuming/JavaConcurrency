package SimplestThread;

/**
 * Created by caesar on 16/3/8.
 */
public class MyFirstThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread:" + Thread.currentThread().getName());
        super.run();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            MyFirstThread myFirstThread = new MyFirstThread();
            myFirstThread.start();
        }

    }
}
