package SimplestThread;

/**
 * Created by caesar on 16/3/8.
 */
public class RunnableThread implements Runnable {
    @Override
    public void run() {
        //Thread 存储一些属性

        //ID:线程唯一的标志符
        System.out.println("Thread:" + Thread.currentThread().getId());
        //Name:线程名称
        System.out.println("Thread:" + Thread.currentThread().getName());
        //Priority:线程的优先级,1-10,1是最低优先级
        System.out.println("Thread:" + Thread.currentThread().getPriority());
        //Status:线程的状态,分别是new,runnable,blocked,waiting,time waiting,terminated
        System.out.println("Thread:" + Thread.currentThread().getState());
    }


    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {

            Thread thread=new Thread(new RunnableThread());
            thread.start();
        }
    }
}
