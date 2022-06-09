package client;

import City.CityConstructor;
import Commands.Arguments;
import Commands.Information;
import Exceptions.InputException;
import IO.Printable;
import IO.Scannable;
import requests.CommandInfoRequest;
import requests.Request;
import requests.RequestContent;

import java.util.ArrayList;
import java.util.List;

public class ClientCommandsManager {
    public String getCommandName(String string) {
        return string.trim().split(" ")[0];
    }

    public Request getCommandInfoRequest(String commandName) {
        RequestContent content =  new CommandInfoRequest(commandName);
        return new Request(content);
    }

    public List<Object> scanSimpleArgs (Information information, String line) throws Exception {
        List<Object> simpleArguments = new ArrayList<>();
        String[] array = line.trim().split(" ");
        if (array.length < information.getSimpleArguments() + 1)
            throw new InputException("Incorrect number of args");
        for (int i = 1; i <= information.getSimpleArguments(); ++i) {
            simpleArguments.add(array[i]);
        }
        Object[] arguments = simpleArguments.toArray();
        return simpleArguments;
    }

    public List<Object> scanComplexArgs (Information information, Printable printable, Scannable scannable, boolean isConsole) throws Exception {
        List<Object> complexArguments = new ArrayList<>();
        for (int i = 0; i < information.getComplexArguments(); ++i) {
            CityConstructor constructor = (CityConstructor) information.getConstructors().get(i).getConstructors()[0].newInstance(printable, scannable);
            if (isConsole)
                complexArguments.add(constructor.scanFromConsoleConstruct());
            else
                complexArguments.add(constructor.scanFromFileConstruct());
        }
        return complexArguments;
    }

    public Arguments scanArguments (Information info, Printable printable, Scannable scannable, String string, boolean isConsole) throws Exception {
        List<Object> simple = scanSimpleArgs(info, string);
        List<Object> complex = scanComplexArgs(info, printable, scannable, isConsole);
       simple.addAll(complex);
       return new Arguments(simple.toArray());
    }
}
