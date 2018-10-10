import beans.Client;
import beans.Event;
import beans.EventType;
import loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import springConfigs.AppConfig;
import beans.Client;
import springConfigs.LoggersConfig;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service
public class App {

    @Resource(name="client")
    private Client client;

    @Resource (name ="defaultLogger")
    private EventLogger defaultEventLogger;

    @Resource(name = "mapLoggers")
    private Map<EventType,EventLogger> loggers;

    public App() {
    }

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
//
//        ConfigurableApplicationContext ctx =
//                new ClassPathXmlApplicationContext("spring.xml");
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class, LoggersConfig.class);

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class,LoggersConfig.class);
        ctx.scan("");
        ctx.refresh();

        App app = (App) ctx.getBean("app");
        Client client = (Client) ctx.getBean("client");

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
