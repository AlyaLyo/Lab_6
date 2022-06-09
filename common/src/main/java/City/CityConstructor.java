package City;

import City.Fields.Climate;
import City.Fields.Coordinates;
import City.Fields.Human;
import City.Fields.IdController;
import IO.Printable;
import IO.Scannable;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.EnumSet;
import java.util.LinkedList;

/**
 * Класс, отвечающий за генерацию нового элемента коллекции.
 */

public class CityConstructor {

    private Printable printable;
    private Scannable scannable;

    public CityConstructor(Printable printable, Scannable scannable) {
        this.printable = printable;
        this.scannable = scannable;
    }

    public City scanFromConsoleConstruct() throws Exception {
        City city = null;
        try {
            int id = IdController.getInstance().getNewId();
            String name = setName();
            Coordinates coordinates = setCoordinates();
            java.time.ZonedDateTime creationDate = ZonedDateTime.now();
            int area = setArea();
            long population = setPopulation();
            Integer metersAboveSeaLevel = setMetersAboveSeaLevel();
            long telephoneCode = setTelephoneCode();
            long agglomeration = setAgglomeration();
            Climate climate = setClimate();
            Human governor = setGovernor();
            city = new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, telephoneCode, agglomeration, climate, governor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return scanFromConsoleConstruct();
        }
        return city;
    }

    public City scanFromFileConstruct() throws Exception {
        int id = IdController.getInstance().getNewId();
        String name = scannable.scanString();

        Double x = scannable.scanDouble();
        Integer y = scannable.scanInteger();
        Coordinates coordinates = new Coordinates(x,y);

        java.time.ZonedDateTime creationDate = ZonedDateTime.now();
        int area = scannable.scanInteger();
        long population = scannable.scanLong();
        Integer metersAboveSeaLevel = scannable.scanInteger();
        long telephoneCode = scannable.scanLong();
        long agglomeration = scannable.scanLong();

        String climateName = scannable.scanString();
        Climate climate = null;
        switch (climateName) {
            case "RAIN_FOREST":
                climate = Climate.RAIN_FOREST;
            case "TROPICAL_SAVANNA":
                climate = Climate.TROPICAL_SAVANNA;
            case "HUMIDSUBTROPICAL":
                climate = Climate.HUMIDSUBTROPICAL;
            case "STEPPE":
                climate = Climate.STEPPE;
        }

        int year = scannable.scanInteger();
        int month = scannable.scanInteger();
        int day = scannable.scanInteger();
        Human governor = new Human(LocalDate.of(year, month, day));

        City city = new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, telephoneCode, agglomeration, climate, governor);
        return city;
    }

    private String setName() throws Exception {
        String name = null;
        printable.print("Enter a name: ");
        while (name == null || name.equals("")){

            name = scannable.scanString();
        }
        try {
            return name;
        } catch (Exception e){
            printable.println(e.getMessage());
            return setName();
        }
    }

    private Coordinates setCoordinates() throws Exception {
        try {
            printable.print("Enter x coordinate: ");
            String xString = scannable.scanString();
            while (xString == null) {
                printable.print("Enter x coordinate: ");
                xString = scannable.scanString();
            }
            Double x = null;
            try {
                x = Double.valueOf(xString);
            } catch (Exception exception){
                printable.println("Double type was requested");
            }

            printable.print("Enter y coordinate: ");
            String yString = scannable.scanString();
            while (yString == null) {
                printable.print("Enter y coordinate: ");
                yString = scannable.scanString();
            }
            Integer y = null;
            try {
                y = Integer.valueOf(yString);
            } catch (Exception exception) {
                printable.println("Integer type was requested");
            }
            Coordinates coordinates = new Coordinates(x,y);
            return coordinates;

        } catch (Exception e){
            printable.println(e.getMessage());
            return setCoordinates();
        }

    }

    private int setArea() throws Exception {
        int area = -1;
        try {
            printable.print("Enter an area: ");
            String areaString = scannable.scanString();
            while (areaString == null) {
                printable.print("Enter an area: ");
                areaString = scannable.scanString();
            }
            area = 10;
            try {
                area = Integer.valueOf(areaString);
            } catch (Exception exception) {
                printable.println("int type was requested");
                return setArea();
            }
        } catch (Exception e){
            printable.println(e.getMessage());
            return setArea();
        }
        return area;
    }

    private long setPopulation() throws Exception {
        long population = -1;
        try {
            printable.print("Enter a population: ");
            String populationString = scannable.scanString();
            while (populationString == null) {
                printable.print("Enter a population: ");
                populationString = scannable.scanString();
            }
            population = 10;
            try {
                population = Long.valueOf(populationString);
            } catch (Exception exception) {
                printable.println("long type was requested");
            }
        } catch (Exception e){
            printable.println(e.getMessage());
            return setPopulation();
        }
        return population;
    }

    private Integer setMetersAboveSeaLevel() throws Exception {
        try {
            printable.print("Enter meters above sea level: ");
            String metersAboveSeaLevelString = scannable.scanString();
            while (metersAboveSeaLevelString == null) {
                printable.print("Enter meters above sea level: ");
                metersAboveSeaLevelString = scannable.scanString();
            }
            Integer metersAboveSeaLevel = null;
            try {
                metersAboveSeaLevel = Integer.valueOf(metersAboveSeaLevelString);
            } catch (Exception exception) {
                printable.println("Integer type was requested");
            }
            return metersAboveSeaLevel;
        } catch (Exception e) {
            printable.println(e.getMessage());
            return setMetersAboveSeaLevel();
        }

    }

    private long setTelephoneCode() throws Exception {
        long telephoneCode = -1;
        try {
            printable.print("Enter a telephone code: ");
            String telephoneCodeString = scannable.scanString();
            while (telephoneCodeString == null) {
                printable.print("Enter a telephone code: ");
                telephoneCodeString = scannable.scanString();
            }
            telephoneCode = 10;
            try {
                telephoneCode = Long.valueOf(telephoneCodeString);
            } catch (Exception exception) {
                printable.println("long type was requested");
            }

        } catch (Exception e) {
            printable.println(e.getMessage());
            return setTelephoneCode();
        }
        return telephoneCode;
    }

    private long setAgglomeration() throws Exception {
        long agglomeration = -1;
        try {
            printable.print("Enter an agglomeration: ");
            String agglomerationString = scannable.scanString();
            while (agglomerationString == null) {
                printable.print("Enter an agglomeration: ");
                agglomerationString = scannable.scanString();
            }
            agglomeration = 10;
            try {
                agglomeration = Long.valueOf(agglomerationString);
            } catch (Exception exception) {
                printable.println("long type was requested");
            }

        } catch (Exception e){
            printable.println(e.getMessage());
            return setAgglomeration();
        }
        return agglomeration;
    }

    private Climate setClimate() throws Exception {
        String climateName;
        Climate climate = null;
        while (climate == null){
            printable.print("Choose a climate " + EnumSet.allOf(Climate.class)  + ": ");
            climateName = scannable.scanString();
            climate = Climate.parse(climateName);
        }
        return climate;
    }

    private Human setGovernor() throws Exception {
        int year = -1;
        int month = -1;
        int day = -1;
        try {

            printable.print("Enter a birth year of governor :b");
            String yearString = scannable.scanString();
            while (yearString == null) {
                printable.print("Enter a birth year of governor : ");
                yearString = scannable.scanString();
            }
            year = 10;
            try {
                year = Integer.valueOf(yearString);
            } catch (Exception exception) {
                printable.println("int type was requested");
            }

            printable.print("Enter a birth month of governor : ");
            String monthString = scannable.scanString();
            while (monthString == null) {
                printable.print("Enter a birth month of governor : ");
                monthString = scannable.scanString();
            }
            month = 10;
            try {
                month = Integer.valueOf(monthString);
            } catch (Exception exception) {
                printable.println("int type was requested");
            }

            printable.print("Enter a birth day of governor : ");
            String dayString = scannable.scanString();
            while (dayString == null) {
                printable.print("Enter a birth day of governor : ");
                dayString = scannable.scanString();
            }
            day = 10;
            try {
                day = Integer.valueOf(dayString);
            } catch (Exception exception) {
                printable.println("int type was requested");
            }

            Human governor = new Human(LocalDate.of(year, month, day));
            return governor;
        } catch (Exception e){
            printable.println(e.getMessage());
            return setGovernor();
        }

    }

    public static City parse(String... strings) {
        City city = new City();
        city.setId(Integer.parseInt(strings[0]));
        city.setName(strings[1]);
        city.setCoordinates(new Coordinates(Double.parseDouble(strings[2]), Integer.parseInt(strings[3])));
        city.setCreationDate(ZonedDateTime.parse(strings[4]));
        city.setArea(Integer.parseInt(strings[5]));
        city.setPopulation(Long.parseLong(strings[6]));
        city.setMetersAboveSeaLevel(Integer.parseInt(strings[7]));
        city.setTelephoneCode(Long.parseLong(strings[8]));
        city.setAgglomeration(Long.parseLong(strings[9]));
        city.setClimate(Climate.parse(strings[10]));
        city.setGovernor(new Human(LocalDate.parse(strings[11])));
        return city;
    }
}
