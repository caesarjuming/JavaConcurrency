package SimplestThread;

/**
 * Created by caesar on 16/3/8.
 */
public class InterruptThread extends Thread{

    @Override
    public void run() {

        for (int j = 0; j <Integer.MAX_VALUE ; j++) {
            System.out.println(j);
            if(isInterrupted()){
                break;
            }
        }


        super.run();
    }

    public static void main(String[] args) {
        InterruptThread interruptThread=new InterruptThread();
        interruptThread.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        interruptThread.interrupt();

        // 对付递归嵌套的进程,如果中断,可以抛出InterruptedException
        // 然后外层捕获这个异常,然后执行中断处理
    }
}
