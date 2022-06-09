package City.Fields;

import City.City;

import java.util.LinkedList;
import java.util.Random;

/**
 * Класс синглтон, отвечающий за генерацию нового id элемента коллекции.
 */

public final class IdController {

    private static IdController instance;
    private static Random random;

    private IdController() {

    }

    public static IdController getInstance() {
        if (instance == null) {
            instance = new IdController();
            random = new Random();
        }
        return instance;
    }

    public int getNewId() {
        int low = 1;
        int high = Integer.MAX_VALUE;
        int id = random.nextInt(high-low)+low;
        return id;
    }

    private boolean checkId (int id, LinkedList<City> cities) {
        return cities.stream().noneMatch(city -> city.getId() == id);
    }

}
