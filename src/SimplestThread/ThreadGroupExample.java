package SimplestThread;

/**
 * Created by caesar on 16/3/10.
 */
public class ThreadGroupExample extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getThreadGroup());
        super.run();
    }

    public static void main(String[] args) {

        ThreadGroup tg=new ThreadGroup("myFirst");
        ThreadGroup tg2=new ThreadGroup("mySecond");


        for (int i = 0; i <10 ; i++) {
            ThreadGroupExample t=new ThreadGroupExample();
            Thread tt;
            if(i%2==0){
                 tt=new Thread(tg,t);
            }else{
                 tt =new Thread(tg2,t);
            }

            tt.start();
        }

    }
}
