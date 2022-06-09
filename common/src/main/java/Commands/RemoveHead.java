package Commands;

import City.City;
import IO.Printable;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Выводит и удаляет первый элемент коллекции.
 */

public class RemoveHead implements Commands {

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;
    private Printable printable;

    /**
     * @param cities Коллекция городов.
     */

    public RemoveHead(LinkedList<City> cities, Printable printable) {
        this.cities = cities;
        this.printable = printable;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() throws IOException {
        printable.println(cities.removeFirst().getName());
    }

    /**
     * @return Command name.
     */

    public static String name() { return "remove_head"; }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,0,true,true, null, null);
    }

}
