package server;

import City.City;

import java.util.LinkedList;

public class ServerRunner implements Runnable{

    private final LinkedList<City> cities;
    private final int port;

    public ServerRunner(LinkedList<City> cities, int port) {
        this.cities = cities;
        this.port = port;
    }

    @Override
    public void run() {
        Server server = new Server(cities);
        server.setup(port);
        try {
            while (true) {
                server.receive();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
