package Commands;

import City.City;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Удаляет из коллекции все элементы, меньшие, чем заданный.
 */

public class RemoveLower implements Commands {

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * Id элемента коллекции.
     */

    private String id;

    /**
     * @param cities Коллекция городов.
     * @param id Id элемента коллекции.
     */

    public RemoveLower(LinkedList<City> cities, String id) {
        this.cities = cities;
        this.id = id;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() {
        cities.removeIf(city -> city.getId() < Integer.parseInt(id));
    }

    /**
     * @return Command name.
     */

    public static String name() { return "remove_lower"; }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(1,0,true,false, Arrays.asList("id"), null);
    }

}
