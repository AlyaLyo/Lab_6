package City;

import City.Fields.IdController;

import java.util.LinkedList;

public class CollectionStorage {

    private static CollectionStorage instance;
    private static LinkedList<City> cities;

    private CollectionStorage() {

    }

    synchronized public static CollectionStorage getInstance() {
        if (instance == null) {
            instance = new CollectionStorage();
        }
        return instance;
    }

    public LinkedList<City> getCollection() {
        return cities;
    }

    public void setCollection(LinkedList<City> cities) {
        this.cities = cities;
    }
}
