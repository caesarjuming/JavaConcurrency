package ExecutorThread;


import java.util.concurrent.*;

/**
 * Created by caesar on 16/3/17.
 */
public class CompletionServiceExecutor {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        CompletionService<String> completionService=new ExecutorCompletionService<String>(threadPoolExecutor);

        ReportRequest faceRequest=new ReportRequest(completionService,"face");
        ReportRequest onlineRequest=new ReportRequest(completionService,"online");
        Thread faceThread=new Thread(faceRequest);
        Thread onlineThread=new Thread(onlineRequest);

        ReportProcessor reportProcessor=new ReportProcessor(completionService);
        Thread senderThread=new Thread(reportProcessor);

        faceThread.start();
        onlineThread.start();
        senderThread.start();

        try {
            faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPoolExecutor.shutdown();


        //其中储存者执行后的结果
        try {
            threadPoolExecutor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reportProcessor.setEnd(true);
        System.out.println("Main end");

    }



}
//处理的任务
class ReportGenerator implements Callable<String>{

    private String sender;
    private String title;

    public ReportGenerator(String title, String sender) {
        this.title = title;
        this.sender = sender;
    }

    @Override
    public String call() throws Exception {
        System.out.println("doing"+sender+":"+title);
        TimeUnit.SECONDS.sleep(5);
        return "hello,world:"+"done"+sender+":"+title;
    }
}
//请求处理
class ReportRequest implements Runnable{

    private String name;
    private CompletionService<String> completionService;

    public ReportRequest(CompletionService<String> completionService, String name) {
        this.completionService = completionService;
        this.name = name;
    }

    @Override
    public void run() {
        ReportGenerator reportGenerator=new ReportGenerator("title:"+name,"sender:"+name);
        completionService.submit(reportGenerator);
    }
}

// 处理request产生的结果
class ReportProcessor implements Runnable{
    private CompletionService<String> completionService;
    private boolean end;

    public ReportProcessor(CompletionService<String> completionService) {
        this.completionService = completionService;
        this.end = false;
    }

    @Override
    public void run() {
        while (!end){
            try {
                Future<String> future=completionService.poll(20,TimeUnit.SECONDS);
                if(future!=null){
                    String str=future.get();
                    System.out.println(str);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}