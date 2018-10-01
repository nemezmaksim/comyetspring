import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultEventLogger;
    private Map<EventType,EventLogger> loggers;

    App (Client client, EventLogger eventLogger, Map<EventType,EventLogger> loggers) {
        this.client=client;
        this.defaultEventLogger = eventLogger;
        this.loggers = loggers;
        }



    private void logEvent(Event event, String msg, EventType eventType) {
        String message = msg.replaceAll(client.getId(),client.getFullName());
        event.setMsg(message);
        EventLogger logger = loggers.get(eventType);
                if (logger==null)
                    logger=defaultEventLogger;

        logger.logEvent(event);

    }

    public static void main(String[] args) throws IOException {

        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");
        Event event =(Event) ctx.getBean("event");
        app.logEvent(event,"Hello 1 some John CONSOLE",EventType.INFO);

        event = (Event) ctx.getBean("event");
        app.logEvent(event,"Hello 2 some John CONSOLE & FILE",EventType.ERROR);

        event = (Event) ctx.getBean("event");
        app.logEvent(event,"Hello 3 some John CACHEFILE",null);

        ctx.close();



//        app.logEvent("Some event for 1");
//        app.logEvent("Some event for 2");



    }
}
