package Commands;

import City.City;
import IO.Printable;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Выводит среднее значение поля metersAboveSeaLevel для всех элементов коллекции.
 */

public class AverageOfMetersAboveSeaLevel implements Commands {

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * Вывод.
     */

    private Printable printable;
    private Integer sum = 0;

    /**
     * @param cities Коллекция городов.
     * @param printable Вывод.
     */

    public AverageOfMetersAboveSeaLevel(LinkedList<City> cities, Printable printable) {
        this.cities = cities;
        this.printable = printable;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() throws IOException {
        cities.forEach(city -> sum += city.getMetersAboveSeaLevel());
        Integer average = sum / cities.size();
        printable.println(average.toString());
    }

    /**
     * @return Command name.
     */

    public static String name() {
        return "average_of_meters_above_sea_level";
    }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,0,true,true, null,null);
    }

}
