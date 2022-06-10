package server;

import City.City;
import City.CollectionStorage;
import Commands.Commands;
import Commands.Information;
import Commands.Arguments;
import Exceptions.InputException;
import IO.Printable;
import org.reflections.Reflections;
import requests.CommandInfoRequest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ServerCommandsManager {

    private final LinkedList<City> cities;

    public ServerCommandsManager(LinkedList<City> cities) {
        this.cities = cities;
    }


    public Commands commandConstructor (String commandName, Arguments commandArguments, Information information, Printable printable) throws Exception {
        List<Object> arguments = new ArrayList<>();
        if (information.getIsCity()) {
            //arguments.add(cities);
            arguments.add(CollectionStorage.getInstance().getCollection());
        }
        if (information.getIsPrintable()) {
            arguments.add(printable);
        }
        List<Object> clientArgs = new ArrayList<>();
        clientArgs.addAll(Arrays.asList(commandArguments.arguments));
        arguments.addAll(clientArgs);

        Class<? extends Commands> command = getCommand(commandName);
        return (Commands) command.getConstructors()[0].newInstance(arguments.toArray());
    }
    public Information info (CommandInfoRequest request) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return getInfo(getCommand(request.commandName));
    }

    private Class<? extends Commands> getCommand (String commandName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Reflections reflections = new Reflections("Commands");
        for(Class<? extends Commands> command : reflections.getSubTypesOf(Commands.class)) {
            if (command.getMethod("name").invoke(null).equals(commandName)) {
                return command;
            }
        }
        throw new InputException("Exception: Wrong command");
    }

    private Information getInfo (Class<? extends Commands> command) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (Information) command.getMethod("getInfo").invoke(null);
    }

}
