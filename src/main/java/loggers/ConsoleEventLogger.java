package loggers;

import org.springframework.stereotype.Component;
import beans.Event;

@Component
public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent (Event event){
        System.out.println(event.toString());
    }

}
