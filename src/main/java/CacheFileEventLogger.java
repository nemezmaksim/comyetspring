import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    CacheFileEventLogger(String fileName, int cacheSize){
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }

    public void destroy() {
        if (!cache.isEmpty())
//            cache.forEach(cache->super.logEvent(cache));
//            cache.stream().forEach(super::logEvent);
            cache.forEach(e->super.logEvent(e));
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
