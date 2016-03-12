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

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionThread {
    private List<String> share;
    private int size;
    private int contain;
    private Lock lock;
    private Condition full;
    private Condition empty;

    //TODO:未完成

    public ConditionThread(int i) {
        contain=i;
        size = 0;
        share = new LinkedList<>();
        lock = new ReentrantLock();
        full = lock.newCondition();
        empty = lock.newCondition();
    }

    public void put(){

        lock.lock();
        if(contain>=size){
            //满了
            try {
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String str=String.valueOf(Math.random());
        ((LinkedList)share).push(str);
        System.out.println("put:"+str);
        size+=1;
        empty.signalAll();

        lock.unlock();
    }

    public void get(){

        lock.lock();
        if(contain<=0){
            //满了
            try {
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        String str=(String)((LinkedList)share).pop();
        System.out.println(str);
        full.signalAll();
        lock.unlock();

    }
    public static void main(String[] args) {
        ConditionThread ct=new ConditionThread(5);
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                ct.put();
                ct.put();
                ct.put();
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                ct.get();
                ct.get();
            }
        });

        t.start();
        t2.start();
    }
}
