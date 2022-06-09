package requests;

import Commands.Arguments;
import Commands.Information;

public class CommandExecuteRequest implements RequestContent {

    public Information information;
    public Arguments arguments;
    public String commandName;

    public CommandExecuteRequest() {}

}
