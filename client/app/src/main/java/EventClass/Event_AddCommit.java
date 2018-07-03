package EventClass;

import java.io.Serializable;
import java.util.Date;

import CommonClass.CommitClass;

public class Event_AddCommit extends Event_Class implements Serializable{
    CommitClass  NewCommit ;

    public Event_AddCommit(String Author ,String ProjectName ,Date date ,CommitClass  NewCommit )
    {
        super(Author,ProjectName,date);
        this.NewCommit = NewCommit;
    }
}