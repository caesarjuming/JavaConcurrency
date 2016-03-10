package SimplestThread;

/**
 * Created by caesar on 16/3/10.
 */
public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getId());

    }
}
