package springConfigs;

import beans.EventType;
import loggers.ConsoleEventLogger;
import loggers.EventLogger;
import beans.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import loggers.CombinedEventLogger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.Resource;
import java.util.*;


@Configuration
@ComponentScan(basePackages = {"loggers"})
public class LoggersConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Resource(name = "consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Resource(name = "fileEventLogger")
    private EventLogger fileEventLogger;

    @Resource(name = "cacheFileEventLogger")
    private EventLogger cacheFileEventLogger;

    @Resource(name = "combinedEventLogger")
    private EventLogger combinedEventLogger;



    @Bean
    public Collection<EventLogger> combinedLoggers() {
        Collection<EventLogger> loggers = new ArrayList<>(2);
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return loggers;
    }

    @Bean
    public Map<EventType,EventLogger> mapLoggers() {
        Map<EventType,EventLogger> mapLoggers = new EnumMap<>(EventType.class);
        mapLoggers.put(EventType.INFO,consoleEventLogger);
        mapLoggers.put(EventType.ERROR,combinedEventLogger);
        return mapLoggers;
    }

    @Bean
    public EventLogger defaultLogger(){
        return cacheFileEventLogger;
    }
}
