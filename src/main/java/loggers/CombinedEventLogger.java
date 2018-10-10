package loggers;

import java.util.Collection;
import beans.Event;
import org.springframework.stereotype.Component;
import springConfigs.LoggersConfig;

import javax.annotation.Resource;

@Component
public class CombinedEventLogger implements EventLogger {

    @Resource(name="combinedLoggers")
   private Collection <EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        loggers.stream().forEach(e->e.logEvent(event));
    }
}
