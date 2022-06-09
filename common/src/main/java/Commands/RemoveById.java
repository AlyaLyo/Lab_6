package Commands;

import City.City;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Удаляет элемент из коллекции по его id.
 */

public class RemoveById implements Commands{

    /**
     * Id элемента коллекции.
     */

    private String id;

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * @param cities Коллекция городов.
     * @param id Id элемента колллекции.
     */

    public RemoveById(LinkedList<City> cities, String id) {
        this.cities = cities;
        this.id = id;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() {
        cities.removeIf(city -> city.getId() == Integer.parseInt(id));
    }

    /**
     * @return Command name.
     */

    public static String name() { return "remove_by_id"; }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(1,0,true,false, Arrays.asList("id"), null);
    }

}
