package Commands;

import City.City;
import City.CityConstructor;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Обновляет значение элемента коллекции, id которого равен заданному.
 */

public class UpdateId implements Commands{

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * Id элемента коллекции.
     */

    private String id;

    /**
     * Элемент коллекции.
     */

    private City city;

    /**
     * @param cities Коллекция городов.
     * @param id Id элемента коллекции.
     * @param city Элемент коллекции.
     */

    public UpdateId(LinkedList<City> cities, String id, City city) {
        this.cities = cities;
        this.id = id;
        this.city = city;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() {
        //for (City city : cities) {
            //if (city.getId() == Integer.parseInt(id)) {
                //cities.remove(city);
                //cities.add(city);
                //city.setId(Integer.parseInt(id));
            //}
        //}
        cities.removeIf(city -> city.getId() == Integer.parseInt(id));
        cities.add(city);
        city.setId(Integer.parseInt(id));
    }

    /**
     * @return Command name.
     */

    public static String name() { return "update_id"; }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(1,1,true,false, Arrays.asList("id"), Arrays.asList(CityConstructor.class));
    }
}
