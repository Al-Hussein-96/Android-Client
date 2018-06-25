package CommonRespone;
import EventClass.Event_Class;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Moaz on 6/25/2018.
 */

public class SendNewEvent extends Respone implements Serializable {

    public List<Event_Class> NewEvent;

    public SendNewEvent(List<Event_Class> NewEvent) {
        super(ResponeType.DONE);
        this.NewEvent = NewEvent;
    }
}
