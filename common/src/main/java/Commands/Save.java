package Commands;

import City.City;
import IO.CSVManager;

import java.util.LinkedList;

/**
 * Сохраняет коллекцию в файл.
 */

public class Save implements Commands{

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * @param cities Коллекция городов.
     */

    public Save(LinkedList<City> cities){
        this.cities = cities;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() throws Exception {
        CSVManager.toCSV(cities, "collection.csv");
    }

    /**
     * @return Command name.
     */

    public static String name() { return "save"; }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,0,true,false, null, null);
    }

}
