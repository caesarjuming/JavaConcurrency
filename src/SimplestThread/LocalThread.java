package SimplestThread;

/**
 * Created by caesar on 16/3/10.
 */
public class LocalThread implements Runnable {

    // InheritableThreadLocal
    // 如果一个线程从其他某个线程中创建的,这个类提供继承的值
    private ThreadLocal<Integer> mylocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            mylocal.set(mylocal.get() + 1);
        }
        System.out.println(mylocal.get());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            LocalThread t = new LocalThread();
            Thread tt = new Thread(t);
            tt.start();
        }
    }

}
