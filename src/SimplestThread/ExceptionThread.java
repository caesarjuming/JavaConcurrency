package SimplestThread;

/**
 * Created by caesar on 16/3/10.
 */
public class ExceptionThread extends Thread{



    // unsupported throws,so we must catch the exception
    // 在run方法中runtime异常是会打印出异常栈,然后终止程序
    @Override
    public void run() {
        int i=Integer.parseInt("aaaa");
        super.run();
    }

    public static void main(String[] args) {
        ExceptionThread t=new ExceptionThread();
        // 设置处理异常的类
        t.setUncaughtExceptionHandler(new SimplestThread.UncaughtExceptionHandler());
        t.start();

    }
}
