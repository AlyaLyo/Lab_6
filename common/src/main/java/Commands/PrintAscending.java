package Commands;

import City.City;
import IO.Printable;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Выводит элементы коллекции в порядке возрастания.
 */

public class PrintAscending implements Commands {

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * Вывод
     */

    private Printable printable;

    /**
     * @param cities Коллекция городов.
     * @param printable Вывод.
     */

    public PrintAscending(LinkedList<City> cities, Printable printable) {
        this.cities = cities;
        this.printable = printable;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() throws IOException {
        cities.forEach(city -> System.out.println(city.toString()));
    }

    /**
     * @return Command name.
     */

    public static String name() { return "print_ascending"; }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,0,true,true, null, null);
    }

}
