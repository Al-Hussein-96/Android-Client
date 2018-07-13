package CommonRespone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moaz on 7/13/2018.
 */

public class SendMyFollowProjects extends Respone implements Serializable {

    List<String> MyFollowProjects = new ArrayList<>();

    public SendMyFollowProjects(List<String> mylist) {
        super(ResponeType.DONE);
        for (String temp : mylist) {
            this.MyFollowProjects.add(temp);
        }
    }

    public List<String> getMyFollowProjects() {
        return MyFollowProjects;
    }

}
