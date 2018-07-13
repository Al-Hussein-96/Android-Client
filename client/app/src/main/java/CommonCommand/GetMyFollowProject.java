package CommonCommand;

import java.io.Serializable;

/**
 * Created by Moaz on 7/13/2018.
 */

public class GetMyFollowProject extends Command implements Serializable{

    public GetMyFollowProject() {
        super(CommandType.MyFollowProjects);
    }


}
