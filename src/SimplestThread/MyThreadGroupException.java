package SimplestThread;/*
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
         Create by Caesar,2016/3/11
*/

public class MyThreadGroupException extends ThreadGroup {
    public MyThreadGroupException(String name) {
        super(name);
    }


    //当线程组中任何一个位置抛出一样，这个方法将会处理异常
    //异常链从方法一直到线程异常处理器，线程组处理器
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("ThreadName:"+t.getName());
        super.uncaughtException(t, e);
    }

    public static void main(String[] args) {
        MyThreadGroupException my=new MyThreadGroupException("Group1-异常处理器1");
        Thread t=new Thread(my,new CountThread());
        t.start();
    }
}

class CountThread implements Runnable{

    @Override
    public void run() {
        System.out.println(1/0);
    }
}