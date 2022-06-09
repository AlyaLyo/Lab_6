package Commands;

import City.City;
import IO.Printable;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Выводит в стандартный поток вывода информацию о коллекции.
 */

public class Info implements Commands {

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

    public Info (LinkedList<City> cities, Printable printable) {
        this.cities = cities;
        this.printable = printable;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() throws IOException {
        printable.println("Тип коллекции:"  + cities.getClass() + "\nВремя инициализации:" + InitTimeController.getInstance().getInitTime(cities) + "\nКоличество элементов:" + cities.size());
    }

    /**
     * @return Command name.
     */

    public static String name() {
        return "info";
    }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,0,true,true, null,null);
    }

}
