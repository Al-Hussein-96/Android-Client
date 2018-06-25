package CommonCommand;

import CommonClass.User;
import java.io.Serializable;

/**
 * Created by Moaz on 6/25/2018.
 */

public class GetNewEvent extends Command implements Serializable{

    public GetNewEvent() {
        super(CommandType.GetNewEvent);
    }
}