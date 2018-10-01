import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {

    public static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    int id;
    String msg;
    Date date;
    DateFormat df;

    /*
    * Constructor
    * */

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
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
