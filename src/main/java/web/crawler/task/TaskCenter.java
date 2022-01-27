package web.crawler.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import web.crawler.dao.CrawlerMapper;
import web.crawler.dao.URLInfo;
import java.util.concurrent.*;

public class TaskCenter implements Runnable{

    private Integer num = 2;
    private Integer total = 10;
    private Integer count = 0;

    @Autowired
    CrawlerTask task;
    @Autowired
    CrawlerMapper crawlerMapper;

    public TaskCenter(LinkedBlockingQueue<String> urlQueue, String url, Integer num, Integer total){
        this.num = num;
        this.total = total;
        this.urlQueue = urlQueue;
    }


    LinkedBlockingQueue<String> urlQueue;
//    LinkedBlockingQueue<String> urlQueue = new LinkedBlockingQueue();
//    ThreadFactory threadFactory = new ThreadNameFactory();
//    ThreadPoolExecutor textThreadPool = new ThreadPoolExecutor(num, num+3, 6, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10),threadFactory);

//    @Async
//    public void creatTask(){
//        Runnable findNewURL = new FindNewURL(urlQueue, total, url);
//        Thread thread = new Thread(findNewURL);
//        thread.start();
//         beginTask();
//        //超过 目标数量停止线程池任务
//        shutdownTheadPool();
//    }


//    @Async
//    public void shutdownTheadPool(){
//        Integer selectCount = 0;
//        EntityWrapper<URLInfo> urlInfoWrapper = new EntityWrapper<>();
//        urlInfoWrapper.ge("id",0);
//        while (true){
//            try{
//                selectCount = crawlerMapper.selectCount(urlInfoWrapper);
//            }catch (Exception e){
////                System.out.println("数据库无数据！!!");
//                continue;
//            }
//            if (selectCount >= total){
//                textThreadPool.shutdown();
//            }
//        }
//    }

    @Async
    public void beginTask(){
        for (int i = 0; i < num; i++) {
//            textThreadPool.execute(new TaskCenter(url,num,total));
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @SneakyThrows
    @Override
    public void run() {
        CrawlerTask task = new CrawlerTask();
        while (true){
            if (!urlQueue.isEmpty()){
              String  url = urlQueue.take();
//                System.out.println("取到url："+url);
                count++;
                task.readHtml(url);
                if (count.equals(total) ){
                    return;
                }
            }
        }

    }

    public static void main(String[] args) {
        LinkedBlockingQueue<String> urlQueue = new LinkedBlockingQueue();
        //需要开启的线程数量
        int num = 5;
        //需要爬取的url地址
        String url1= "https://news.163.com/";
//        String url2 = "https://www.sohu.com/";
        //爬取的数据总量
        Integer total = 10000;

        Runnable findNewURL = new FindNewURL(urlQueue, (total/2)+2,  url1);
        Thread findUrl = new Thread(findNewURL);
        findUrl.start();

//        Runnable findNewURL2 = new FindNewURL(urlQueue, (total/2)+2,  url2);
//        Thread findUrl2 = new Thread(findNewURL2);
//        findUrl2.start();

        TaskCenter taskCenter1 = new TaskCenter(urlQueue,url1,total/num,total);
//        TaskCenter taskCenter2 = new TaskCenter(urlQueue,url2,total/num,total);

//        //循环创建线程
        for (int i = 0; i < num; i++) {
            new Thread(taskCenter1).start();
        }
//        Thread center1 = new Thread(taskCenter1);
//        center1.start();
//
//        Thread center2 = new Thread(taskCenter1);
//        center2.start();
//
//        Thread center3 = new Thread(taskCenter2);
//        center3.start();
//
//        Thread center4 = new Thread(taskCenter2);
//        center4.start();


    }
}
