package Commands;

import IO.Printable;

import java.io.IOException;

/**
 * Выводит справку по доступным командам.
 */

public class Help implements Commands {

    /**
     * Вывод.
     */

    private Printable printable;

    /**
     * @param printable Вывод.
     */

    public Help (Printable printable)  {
        this.printable = printable;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() throws IOException {
        printable.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_first : удалить первый элемент из коллекции\n" +
                "remove_head : вывести первый элемент коллекции и удалить его\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "average_of_meters_above_sea_level : вывести среднее значение поля metersAboveSeaLevel для всех элементов коллекции\n" +
                "group_counting_by_agglomeration : сгруппировать элементы коллекции по значению поля agglomeration, вывести количество элементов в каждой группе\n" +
                "print_ascending : вывести элементы коллекции в порядке возрастани");
    }

    /**
     * @return Command name.
     */

    public static String name() {
        return "help";
    }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,0,false,true, null,null);
    }

}

