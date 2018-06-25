package EventClass;
import CommonClass.User;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by Moaz on 6/25/2018.
 */

public class Event_Class implements Serializable{
    public String Author ;
    public String ProjectName ;
    public Date date;
    public Event_Class(String Author ,String ProjectName ,Date date)
    {
        this.Author = Author;
        this.ProjectName = ProjectName;
        this.date = date;
    }
}
