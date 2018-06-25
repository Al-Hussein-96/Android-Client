package EventClass;

import CommonClass.User;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by Moaz on 6/25/2018.
 */


public class Event_AddContributor extends Event_Class implements Serializable{
    String  NameContributor ;

    public Event_AddContributor(String Author ,String ProjectName ,Date date ,String  NameContributor )
    {
        super(Author,ProjectName,date);
        this.NameContributor = NameContributor;
    }
}
