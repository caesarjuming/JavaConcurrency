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

public class Cinema {
    private long count1;
    private long count2;

    private final Object o1, o2;

    public void soutCount(){
        System.out.println(count1+","+count2);
    }

    public Cinema() {
        count1 = 10;
        count2 = 10;
        o1 = new Object();
        o2 = new Object();
    }

    //同一时间只有一个线程能访问o1对象保护的这段代码
    public boolean sell1(int i){
        synchronized (o1){
            if(i<count1){
                count1-=i;
                return true;
            }else{
                return false;
            }
        }

    }

    public boolean sell2(int j){
        synchronized (o2){
            if(j<count2){
                count2-=j;
                return true;
            }else{
                return false;
            }
        }
    }


}


class Seller1 implements Runnable{
    private Cinema cinema;
    Seller1(Cinema cinema){
        this.cinema=cinema;
    }


    @Override
    public void run() {
        cinema.sell1(2);
        cinema.sell1(2);
        cinema.sell2(1);
        cinema.sell2(1);

    }
}

class Seller2 implements Runnable{
    private Cinema cinema;
    Seller2(Cinema cinema){
        this.cinema=cinema;
    }


    @Override
    public void run() {
        cinema.sell1(2);
        cinema.sell1(1);
        cinema.sell2(2);
        cinema.sell2(1);

    }
}

class Main{
    public static void main(String[] args) {
        Cinema cinema=new Cinema();
        Seller1 s1=new Seller1(cinema);
        Seller2 s2=new Seller2(cinema);
        Thread t=new Thread(s1);
        Thread t2=new Thread(s2);
        t.start();
        t2.start();

        try {
            t.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cinema.soutCount();
    }
}