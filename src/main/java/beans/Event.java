package beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("prototype")
public class Event {

    public static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    int id;
    String msg;
    @Resource(name = "newDate")
    Date date;
    @Resource(name = "dateFormat")
    DateFormat df;

    /*
    * Constructor
    * */
    public Event () {
        this.id=AUTO_ID.getAndIncrement();
    }
    Event (Date d, DateFormat df) {
        this.id=AUTO_ID.getAndIncrement();
        this.date = d;
        this.df=df;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "beans.Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
