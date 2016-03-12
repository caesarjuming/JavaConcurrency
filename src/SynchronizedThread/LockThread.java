package SynchronizedThread;/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
         Create by Caesar,2016/3/12
*/

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockThread {
    private final Lock lock=new ReentrantLock();
    private int i;
    LockThread(int i){
        this.i=i;
    }
    public int getI(){
        lock.lock();
        int j=this.i;
        lock.unlock();
        return j;

    }

    // lock也允许递归持有锁
    // lock.tryLock();尝试获得锁，成功返回true，失败返回false
    public void add(int j){

        lock.lock();
        i+=j;
        lock.unlock();
    }

    public void min(int j){
        lock.lock();
        i-=j;
        lock.unlock();
    }


}

class MainLock{
    public static void main(String[] args) {
        LockThread lockThread=new LockThread(10);
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    lockThread.add(1);
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    lockThread.min(3);
                }
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lockThread.getI());

    }
}
