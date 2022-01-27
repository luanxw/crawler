package web.crawler.dao;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("url_info")
public class URLInfo {

    private String id;

    private String title;

    private String content;

    private Date time;


}
