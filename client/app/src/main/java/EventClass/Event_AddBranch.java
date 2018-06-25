package EventClass;

import CommonClass.User;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Moaz on 6/25/2018.
 */


public class Event_AddBranch extends Event_Class implements Serializable{
    String NameBranch ;

    public Event_AddBranch(String Author ,String ProjectName ,Date date ,String NameBranch )
    {
        super(Author,ProjectName,date);
        this.NameBranch = NameBranch;
    }

}
