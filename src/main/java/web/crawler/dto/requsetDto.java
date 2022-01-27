package web.crawler.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class requsetDto {

    @ApiModelProperty("爬取的目标网址")
    List<String> urls;

    @ApiModelProperty("需要爬取的网页总数")
    Integer total;

}
