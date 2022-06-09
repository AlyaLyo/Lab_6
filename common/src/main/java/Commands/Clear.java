package Commands;

import City.City;

import java.util.LinkedList;

/**
 * Очищает коллекцию.
 */

public class Clear implements Commands {

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * @param cities Коллекция городов.
     */

    public Clear(LinkedList<City> cities) {
        this.cities = cities;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() {
        cities.clear();
    }

    /**
     * @return Command name.
     */

    public static String name() {
        return "clear";
    }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,0,true,false, null, null);
    }

}
