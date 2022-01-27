package web.crawler.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CrawlerMapper  extends BaseMapper<URLInfo> {
}
