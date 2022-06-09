package Commands;

import City.City;
import City.CityConstructor;
import Exceptions.InputException;
import IO.ConsolePrint;
import IO.ConsoleScan;
import IO.Printable;
import IO.Scannable;
import org.reflections.Reflections;

import java.util.*;

/**
 * Класс, отвечающий за считывание команды.
 */

public class CommandsManager {

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * @param cities Коллекция городов.
     */

    public CommandsManager(LinkedList<City> cities) {
        this.cities = cities;
    }

    /**
     * Метод, считывающий команду из консоли.
     * @return Команда.
     */

//    public Commands clientCommand (String nextCommand) throws Exception {
//        Printable printable = new ConsolePrint();
//        Scannable scannable = new ConsoleScan();
//        //printable.print("Enter command: ");
//
//        //String nextCommand = scannable.scanString();
//
//        Class<? extends Commands> command = getCommand(nextCommand);
//        Information information = getCommandInfo(command);
//        List<String> simpleArguments = scanConsoleSimpleArgs(information,nextCommand);
//        List<Object> complexArguments = scanConsoleComplexArgs(information,printable,scannable);
//
//        Commands newCommand = commandConstructor(command,simpleArguments,complexArguments,information,printable);
//        return newCommand;
//    }

    /**
     * Метод, считывающий команду из файла.
     * @param scannable Ввод.
     * @return Команда.
     */

//    public List<Commands> fileCommands(Scannable scannable) throws Exception {
//        List<Commands> commands = new ArrayList<>();
//        while (scannable.hasNextLine()) {
//            String line = scannable.scanString();
//
//            if (line == null) {
//                break;
//            }
//            if (line.equals("")) {
//                continue;
//            }
//
//            List<String> words = Arrays.asList(line.split("[ \r]"));
//            Class<? extends Commands> command = getCommand(words.get(0));
//            Information information = getCommandInfo(command);
//            List<String> simpleArguments = scanFileSimpleArgs(information,words);
//            List<Object> complexArguments = scanFileComplexArgs(information,new ConsolePrint(),scannable);
//            Commands newCommand = commandConstructor(command,simpleArguments,complexArguments,information,new ConsolePrint());
//            commands.add(newCommand);
//        }
//        return commands;
//    }

    /**
     * Метод, определяющий класс команды.
     * @param commandName Command name.
     * @return Класс команды.
     */

    private Class<? extends Commands> getCommand(String commandName) throws Exception {
        Reflections reflections = new Reflections("Commands");
        for(Class<? extends Commands> command : reflections.getSubTypesOf(Commands.class)) {
            if (command.getMethod("name").invoke(null).equals(commandName.trim().split(" ")[0])) {
                return command;
            }
        }
        throw new InputException("Exception: Wrong command");
    }

    /**
     * Метод, определяющий информацию о команде.
     * @param command Команда {@link Commands}.
     * @return Command Information.
     */

    private Information getCommandInfo(Class<? extends Commands> command) throws Exception {
        return (Information) command.getMethod("getInfo").invoke(null);
    }

    /**
     * Метод, определяющий простые аргументы команды из консоли.
     * @param information Command information.
     * @param line Введенная строка.
     * @return Простые аргументы команды.
     */

    private List<String> scanConsoleSimpleArgs (Information information, String line) throws Exception {
        List<String> simpleArguments = new ArrayList<>();
        for (int i = 1; i <= information.getSimpleArguments(); ++i) {
            simpleArguments.add(line.trim().split(" ")[i]);
        }
        return simpleArguments;
    }

    /**
     * Метод, определяющий простые аргументы команды из файла.
     * @param information Command information.
     * @param fromFile Строка из файла.
     * @return Простые аргументы команды.
     */

    private List<String> scanFileSimpleArgs (Information information, List<String> fromFile) {
        List<String> simpleArguments = new ArrayList<>();
        for (int i = 1; i <= information.getSimpleArguments(); ++i) {
            simpleArguments.add(fromFile.get(i));
        }
        return simpleArguments;
    }

    /**
     * Метод, определяющий составные аргументы команды из консоли.
     * @param information Command information.
     * @param printable Вывод.
     * @param scannable Ввод.
     * @return Составные аргументы команды.
     */

    private List<Object> scanConsoleComplexArgs (Information information,Printable printable, Scannable scannable) throws Exception {
        List<Object> complexArguments = new ArrayList<>();
        for (int i = 0; i < information.getComplexArguments(); ++i) {
            CityConstructor constructor = (CityConstructor) information.getConstructors().get(i).getConstructors()[0].newInstance(printable,scannable,cities);
            complexArguments.add(constructor.scanFromConsoleConstruct());
        }
        return complexArguments;
    }

    /**
     * Метод, определяющий составные аргументы команды из файла.
     * @param information Command information.
     * @param printable Вывод.
     * @param scannable Ввод.
     * @return Составные аргументы команды.
     */

    private List<Object> scanFileComplexArgs (Information information, Printable printable, Scannable scannable) throws Exception {
        List<Object> complexArguments = new ArrayList<>();
        for (int i = 0; i < information.getComplexArguments(); ++i) {
            CityConstructor constructor = (CityConstructor) information.getConstructors().get(i).getConstructors()[0].newInstance(printable,scannable, cities);
            complexArguments.add(constructor.scanFromFileConstruct());
        }
        return complexArguments;
    }

    /**
     * Метод, составляющий конструктор команды.
     * @param information Command information.
     * @param printable Вывод.
     * @return Конструктор команды.
     */

    public Commands commandConstructor (String commandName, Arguments commandArguments, Information information, Printable printable) throws Exception {
        List<Object> arguments = new ArrayList<>();
        if (information.getIsCity()) {
            arguments.add(cities);
        }
        if (information.getIsPrintable()) {
            arguments.add(printable);
        }
        List<Object> clientArgs = new ArrayList<>();
        for (Object argument : commandArguments.arguments) {
            clientArgs.add(argument);
        }
        arguments.addAll(clientArgs);

        Class<? extends Commands> command = getCommand(commandName);
        return (Commands) command.getConstructors()[0].newInstance(arguments.toArray());
    }

}
