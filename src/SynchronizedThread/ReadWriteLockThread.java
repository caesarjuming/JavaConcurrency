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
         ���汣��       ����BUG
         Create by Caesar,2016/3/12
*/

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockThread {
    private int i1;
    private final ReadWriteLock readWriteLock;

    public ReadWriteLockThread(int i1) {
        this.i1=i1;
        //ReentrantReadWriteLock �и�����������fair Ĭ��Ϊfalse
        //���Ϊtrue����ô�ͻ��ǹ�ƽ�����ȴ�ʱ��Խ�����̻߳�����Ļ���Խ��
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    public void Write(int i){
        readWriteLock.writeLock().lock();
        i1+=i;
        System.out.println("Now:write-"+i1);
        readWriteLock.writeLock().unlock();
    }

    public void Read(){
        readWriteLock.readLock().lock();
        System.out.println("Now:read-"+i1);
        readWriteLock.readLock().unlock();
    }
}

class ReadWriteMain{
    public static void main(String[] args) {
        ReadWriteLockThread t=new ReadWriteLockThread(10);
        Thread read1=new Thread(new Runnable() {
            @Override
            public void run() {
                t.Read();
                t.Read();
                t.Read();
            }
        });
        Thread read2=new Thread(new Runnable() {
            @Override
            public void run() {
                t.Read();
                t.Read();
                t.Read();
            }
        });

        Thread writer =new Thread(new Runnable() {
            @Override
            public void run() {
                t.Write(1);
                t.Write(2);
            }
        });

        read1.start();
        read2.start();
        writer.start();



    }
}

