package CommonCommand;

import java.io.Serializable;

/**
 * Created by Moaz on 7/14/2018.
 */

public class GetAllProject extends Command implements Serializable{

    public GetAllProject() {
        super(CommandType.ALLPROJECT);
    }

}
