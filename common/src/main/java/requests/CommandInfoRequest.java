package requests;

public class CommandInfoRequest implements RequestContent {

    public String commandName;

    public CommandInfoRequest() {}

    public CommandInfoRequest(String commandName) {
        this.commandName = commandName;
    }
}
