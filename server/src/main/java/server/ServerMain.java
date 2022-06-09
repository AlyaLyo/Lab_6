package server;

import City.City;
import City.NameCityComparator;
import Commands.Save;
import IO.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class ServerMain {

    final static int port = 6006;

    public static void main(String[] args) throws Exception {

        System.setProperty("org.slf4j.simpleLogger.log.org.reflections", "off");

        LinkedList<City> cities = new LinkedList<>();

        Comparator nameComparator = new NameCityComparator();
        Collections.sort(cities,nameComparator);
        //Collections.sort(cities);

        try {
            cities = CSVManager.parse("collection.csv");
        } catch (Exception e){
            System.out.println("File is requested");
            System.exit(0);
        }

        Thread thread = new Thread(new ServerRunner(cities, port));

        thread.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter server command:");
            String input = scanner.nextLine();

            if (input.equals("save")) {
                new Save(cities).execute();
                System.out.println("Saved!");
            }

            if (input.equals("exit")) {
                new Save(cities).execute();
                System.out.println("Saved!");
                thread.interrupt();
                System.out.println("Goodbye");
                System.exit(0);
            }
        }
    }

}
