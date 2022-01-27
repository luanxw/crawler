package web.crawler.task;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import web.crawler.config.TaskConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class FindNewURL implements Runnable {

    public FindNewURL( LinkedBlockingQueue<String> urlQueue , Integer total,String url){
        this.urlQueue = urlQueue;
        this.total = total;
        this.url = url;
    }



    LinkedBlockingQueue<String> urlQueue;
    Integer total ;
    Integer count  = 0;
    private String url = "https://voice.hupu.com/nba";


    public void UrlTask(Integer total){
        while (count <= total){
            List<String> newUrl = null;
            if (count == 0){
                newUrl = FindNewURL.getNewUrl(url);
            }else {
                newUrl = FindNewURL.getNewUrl(urlQueue.poll());
                count--;
            }
            if (CollectionUtils.isEmpty(newUrl)){
                continue;
            }
            for (String url : newUrl){
                if (count <= total){
                    try {
                        urlQueue.add(url);
                        count++;
//                        System.out.println("放入队列地址："+url);
                    } catch (Exception e) {
                        return;
                    }
                }else {
                    System.out.println("URL总共数量："+count);
                    return;
                }
            }
        }
    }

    public static List<String> getNewUrl(String url){
        List<String> URLList =  new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a[href]");
//        System.out.println("Links: "+links.size());
        for (Element link : links) {
            String taget = link.attr("abs:href");
            if (taget.endsWith(".html")){
                URLList.add(taget);
//                System.out.println(taget+", "+ trim(link.text(), 35));
            }
        }
        return URLList;
    }
    private static String trim(String s, int width) {
        if (s.length() > width) {
            return s.substring(0, width - 1) + ".";
        }
        else {
            return s;
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
    @Async
    @Override
    public void run() {
        while (count <= total){
            List<String> newUrl = null;
            if (count == 0){
                newUrl = FindNewURL.getNewUrl(url);
            }else {
                newUrl = FindNewURL.getNewUrl(urlQueue.poll());
                count--;
            }
            if (CollectionUtils.isEmpty(newUrl)){
                continue;
            }
            for (String url : newUrl){
                if (count <= total){
                    try {
                        urlQueue.put(url);
                        count++;
                        System.out.println("已获取url数量:"+count+",即将放入队列地址："+url);
                    } catch (InterruptedException e) {
                        return;
                    }
                }else {
                    System.out.println("URL总共数量："+count);
                    return;
                }
            }
        }
    }
}
