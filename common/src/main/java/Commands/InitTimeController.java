package Commands;

import City.City;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Класс синглтон, отвечающий за генерацию времени иницилизации колеекции.
 */

public final class InitTimeController {

    /**
     * Экземпляр класса.
     */

    private static InitTimeController instance;

    /**
     * Словарь времени инциализации коллекций.
     */

    private Map<LinkedList<City>,LocalDate> collectionDate = new HashMap<LinkedList<City>,LocalDate>();

    private InitTimeController() {

    }

    /**
     * Метод, создающий экземпляр класса и гарантирующий его единственность.
     * @return Экземпляр класса.
     */

    public static InitTimeController getInstance() {
        if (instance == null) {
            instance = new InitTimeController();
        }
        return instance;
    }

    /**
     * Метод, генерирующий время инициализации коллекции.
     * @param cities Коллекция городов.
     * @return Время инициализации коллекции.
     */

    public LocalDate getInitTime(LinkedList<City> cities) {
        collectionDate.put(cities,LocalDate.now());
        return collectionDate.get(cities);
    }
}
