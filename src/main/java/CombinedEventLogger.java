import java.util.Collection;

public class CombinedEventLogger implements EventLogger {

    Collection <EventLogger> loggers;

    CombinedEventLogger(Collection <EventLogger> loggers) {
        this.loggers=loggers;
    }
    @Override
    public void logEvent(Event event) {
        loggers.stream().forEach(e->e.logEvent(event));
    }
}
