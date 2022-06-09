package Commands;

import City.City;
import IO.Printable;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
 */

public class Show implements Commands {

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * Вывод.
     */

    private Printable printable;

    /**
     * @param cities Коллекция городов.
     * @param printable Вывод.
     */

    public Show(LinkedList<City> cities, Printable printable){
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

    public static String name() { return "show"; }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,0,true,true,null, null);
    }

}
