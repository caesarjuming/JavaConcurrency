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

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventStorage {
    private int maxSize;
    private List<Date> storage;

    public synchronized int getSize(){
        return storage.size();
    }

    public synchronized int getMaxSize(){
        return storage.size();
    }

    public EventStorage() {
        this.maxSize=10;
        //linkedList没有初始化大小，理想是无限长度的
        storage=new LinkedList<>();
    }

    public synchronized void set(){
        if(maxSize==getMaxSize()){
            try {
                //阻塞，线程挂起
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("Set Size:"+storage.size());
        //唤醒所有挂起的线程
        notifyAll();
    }

    public synchronized Date get(){
        if(getMaxSize()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get Size:"+storage.size());
        Date d=((LinkedList<Date>)storage).poll();
        notifyAll();
        return d;
    }
}


class Producer implements Runnable{
    private EventStorage eventStorage;

    public Producer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            eventStorage.set();
        }
    }
}
class Consumer implements Runnable{
    private EventStorage eventStorage;

    public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            eventStorage.get();
        }
    }



}


class MainEvent{
    public static void main(String[] args) {
        EventStorage e=new EventStorage();
        Producer p=new Producer(e);
        Consumer c=new Consumer(e);

        Thread t1=new Thread(p);
        Thread t2= new Thread(c);

        t1.start();
        t2.start();

        try {
            t2.join();
            t1.join();

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println(e.getSize());

    }
}