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

public class FirstSynchronized {

    private int i=0;

    //sync������static������ͬʱֻ�ܱ�һ���߳�ʹ�ã����������߳̿���ʹ�÷�static����
    //������ǹ�ͬ�޸ĵ�ͬһ�����ݣ���ô��������ݲ�һ��
    //sync�����ή������

    //���ݹ����һ��sync����ʱ�������Ե���������������sync���������������»����
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
