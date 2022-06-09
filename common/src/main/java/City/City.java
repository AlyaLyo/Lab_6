package City;

import City.Fields.Climate;
import City.Fields.Coordinates;
import City.Fields.Human;
import Exceptions.InputException;

import java.time.ZonedDateTime;

/**
 * Класс города.
 */

public class City implements Comparable<City> {

    private int id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private int area;
    private long population;
    private Integer metersAboveSeaLevel;
    private long telephoneCode;
    private long agglomeration;
    private Climate climate;
    private Human governor;

    public int getArea() {
        return area;
    }

    public City() {};

    public City (int id,String name, Coordinates coordinates, ZonedDateTime creationDate, int area, long population, Integer metersAboveSeaLevel, long telephoneCode, long agglomeration, Climate climate, Human governor) {
        this.setId(id);
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setCreationDate(creationDate);
        this.setArea(area);
        this.setPopulation(population);
        this.setMetersAboveSeaLevel(metersAboveSeaLevel);
        this.setTelephoneCode(telephoneCode);
        this.setAgglomeration(agglomeration);
        this.setClimate(climate);
        this.setGovernor(governor);
    }

    /**
     * Сравнение выполняется по id города.
     * @param city
     * @return
     */

    @Override
    public int compareTo(City city) {
        if (city == null) {
            return 1;
        }
        if (this.getId() == city.getId()) {
            return 0;
        }
        if (this.getId() < city.getId()) {
            return -1;
        }
        return 1;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public Integer getMetersAboveSeaLevel() { return metersAboveSeaLevel; }

    public Long getAgglomeration() {
        return  agglomeration;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new InputException("Exception: Field 'id' can not be less or equal zero");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null) {
            throw new InputException("Exception: Field 'name' can not be null");
        }
        if (name.isEmpty()) {
            throw new InputException("Exception: Field 'name' can not be empty");
        }
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new InputException("Exception: Field 'coordinates' can not be null");
        }
        this.coordinates = coordinates;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        if (creationDate == null) {
            throw new InputException("Exception: Field 'creation date' can not be null");
        }
        this.creationDate = creationDate;
    }

    public void setArea(int area) {
        if (area <= 0) {
            throw new InputException("Exception: Field 'area' can not be less or equal zero");
        }
        this.area = area;
    }

    public void setPopulation(long population) {
        if (area <= 0) {
            throw new InputException("Exception: Field 'population' can not be less or equal zero");
        }
        this.population = population;
    }

    public void setMetersAboveSeaLevel(Integer metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public void setTelephoneCode(long telephoneCode) {
        if (telephoneCode <= 0) {
            throw new InputException("Exception: Field 'telephone code' can not be less o equal zero");
        }
        if (telephoneCode > 100000) {
            throw new InputException("Exception: Field 'telephone code' can not be more 100 000");
        }
        this.telephoneCode = telephoneCode;
    }

    public void setAgglomeration(long agglomeration) {
        this.agglomeration = agglomeration;
    }

    public void setClimate(Climate climate) {
        if (climate == null) {
            throw new InputException("Exception: Field 'climate' can not be null");
        }
        this.climate = climate;
    }

    public void setGovernor(Human governor) {
        if (governor == null) {
            throw new InputException("Exception: Field 'governor' can not be null");
        }
        this.governor = governor;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public long getPopulation() {
        return population;
    }

    public long getTelephoneCode() {
        return telephoneCode;
    }

    public Climate getClimate() {
        return climate;
    }

    public Human getGovernor() {
        return governor;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + ',' + name + ',' + coordinates.toString() + ',' + creationDate + ',' + area + ',' + population +
                ',' + metersAboveSeaLevel + ',' + telephoneCode + ',' + agglomeration + ',' + climate +
                ',' + governor.toString();
    }
}
