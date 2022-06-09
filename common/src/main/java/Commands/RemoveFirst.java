package Commands;

import City.City;

import java.util.LinkedList;

/**
 * Удаляет первый элемент коллекции.
 */

public class RemoveFirst implements Commands{

    /**
     * Коллекция городов.
     */

    private LinkedList<City> cities;

    /**
     * @param cities Коллекция городов.
     */

    public RemoveFirst(LinkedList<City> cities) {
        this.cities = cities;
    }

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() {
        cities.removeFirst();
    }

    /**
     * @return Command name.
     */

    public static String name() { return "remove_first"; }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,0,true,false, null, null);
    }

}
