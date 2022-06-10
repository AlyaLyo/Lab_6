package City;

import City.Fields.IdController;

import java.util.LinkedList;

public class CollectionStorage {

    private static CollectionStorage instance;
    private static LinkedList<City> cities;

    private CollectionStorage() {

    }

    private static class Holder {
        public  static final CollectionStorage instance = new CollectionStorage();
    }

    public static CollectionStorage getInstance() {
        return Holder.instance;
    }

    public LinkedList<City> getCollection() {
        return cities;
    }

    public void setCollection(LinkedList<City> cities) {
        this.cities = cities;
    }
}
