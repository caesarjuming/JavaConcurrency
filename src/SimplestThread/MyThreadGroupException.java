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
         ���汣��       ����BUG
         Create by Caesar,2016/3/11
*/

public class MyThreadGroupException extends ThreadGroup {
    public MyThreadGroupException(String name) {
        super(name);
    }


    //���߳������κ�һ��λ���׳�һ��������������ᴦ���쳣
    //�쳣���ӷ���һֱ���߳��쳣���������߳��鴦����
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("ThreadName:"+t.getName());
        super.uncaughtException(t, e);
    }

    public static void main(String[] args) {
        MyThreadGroupException my=new MyThreadGroupException("Group1-�쳣������1");
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