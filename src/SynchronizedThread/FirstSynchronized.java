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

public class FirstSynchronized {

    private int i=0;

    //sync声明的static方法，同时只能被一个线程使用，但是其他线程可以使用非static方法
    //如果他们共同修改的同一个数据，那么会造成数据不一致
    //sync方法会降低性能

    //当递归调用一个sync方法时，还可以调用这个对象的其他sync方法，而不用重新获得锁
    public synchronized void add(int a){
        i+=a;
        System.out.println(i);
    }

    public synchronized void min(int b){
        i-=b;
        System.out.println(i);
    }

}

class UseSync implements Runnable{

    FirstSynchronized firstSynchronized;

    UseSync(FirstSynchronized firstSynchronized){
        this.firstSynchronized=firstSynchronized;
    }

    @Override
    public void run() {
        firstSynchronized.add(1);
        firstSynchronized.min(2);
    }

    public static void main(String[] args) {

        FirstSynchronized firstSynchronized=new FirstSynchronized();
        for (int i = 0; i <10 ; i++) {
            Thread t =new Thread(new UseSync(firstSynchronized));
            t.start();
        }

    }

}
