package CommonCommand;

/**
 * Created by Moaz on 7/13/2018.
 */

public class FollowProject extends Command {

    public String ProjectName;
    public boolean Follow; /// True then follow else then delete follow

    public FollowProject(String ProjectName, boolean Follow) {
        super(CommandType.Follow_Project);
        this.ProjectName = ProjectName;
        this.Follow = Follow;
    }
}
