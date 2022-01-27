package web.crawler.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public abstract class TaskConfig {

   public LinkedBlockingQueue<String> urlQueue = new LinkedBlockingQueue();
}
