package loggers;

import java.util.ArrayList;
import java.util.List;
import beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class CacheFileEventLogger extends FileEventLogger {

    @Value("${cache.size:4}")
    private int cacheSize;
    private List<Event> cache;

    CacheFileEventLogger(){

    }

    CacheFileEventLogger(String fileName, int cacheSize){
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }
    @PreDestroy
    public void destroy() {
        if (!cache.isEmpty())
//            cache.forEach(cache->super.logEvent(cache));
//            cache.stream().forEach(super::logEvent);
            cache.forEach(e->super.logEvent(e));
    }

    @PostConstruct
    public void initCache() {
        this.cache = new ArrayList<>(cacheSize);
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize){
//          cache.forEach(e->super.logEvent(e));
            cache.stream().forEach(super::logEvent);
//          cache.clear();
        }
    }
}
