package web.crawler.task;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.crawler.JDBC.JDBCUtils;
import web.crawler.dao.CrawlerMapper;

import java.io.IOException;
import java.sql.SQLException;


@Slf4j
@Component
public class CrawlerTask{

    @Autowired
    CrawlerMapper mapper;


    /**
     * 获取虎扑新闻或网易新闻列表列表页
     * @param url 获取虎扑新闻或网易新闻列表
     */
    public void readHtml(String url){
        if (StringUtil.isBlank(url)){
            return;
        }
        try {
            Document document = Jsoup.connect(url).get();
            String title = document.title();
            String content = document.toString();
            JDBCUtils.saveContent(title,content);
            System.out.println("网页标题："+title+",地址："+ url);
        } catch (IOException | SQLException e) {
            log.error("爬取url错误：url:{},错误信息：{}",url, e.getMessage());
        }
    }

    public static void main(String[] args) {
//        String url = "https://voice.hupu.com/nba";
        String url = "https://www.163.com/data/article/GRBK57JQ000181IU.html";
        CrawlerTask crawlerBase = new CrawlerTask();
        crawlerBase.readHtml(url);
    }



}
