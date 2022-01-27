package web.crawler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import web.crawler.task.TaskCenter;

/**
 * Created by zlliu on 2018/8/23.
 */
@Api(tags = "爬虫开启接口，根据传入url列表爬取网页")
@RestController
@RequestMapping("/crawler")
public class UrlController  {

    @ApiOperation(value = "根据传入的url爬取网关内容")
    @GetMapping(value = "/start/task")
    public String crawlerUrls(@RequestParam("total")Integer total,
                               @RequestParam("num")Integer num,
                               @RequestParam("url")String url) {
//        TaskCenter taskCenter = new TaskCenter(url, num, total);
//        taskCenter.creatTask();
        return "任务创建成功";
    }

}
