package Commands;

import City.City;
import City.CityConstructor;
import IO.Printable;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Добавляет в коллекцию новый элемент.
 */

public class Add implements Commands {

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * Эллемент коллекции городов.
     */

    private City city;

    /**
     * Вывод.
     */

    private Printable printable;

    /**
     * @param cities Коллекция городов.
     * @param printable Вывод.
     * @param city Элемент коллекции городов.
     */

    public Add(LinkedList<City> cities, Printable printable, City city) {
        this.cities = cities;
        this.city = city;
        this.printable = printable;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() {
        cities.add(city);
    }

    /**
     * @return Command name.
     */

    public static String name() { return "add"; }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,1,true,true, null, Arrays.asList(CityConstructor.class));
    }

}
