package server;

import City.City;
import server.controllers.RequestController;
import server.nio.ServerNIO;
import server.nio.ServerScan;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.LinkedList;

public class Server {

    private LinkedList<City> cities;
    private SocketAddress socketAddress;
    private DatagramChannel datagramChannel;
    private static final int arraySize = 65536;

    public Server(LinkedList<City> cities) {
        this.cities = cities;
    }

    public void setup(int port) {
        try {
            socketAddress = new InetSocketAddress("localhost", port);
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(socketAddress);
        } catch (IOException exception) {

        }
    }

    public void receive() throws Exception {
        ServerScan scanner = new ServerScan(datagramChannel);
        ServerNIO nio = new ServerNIO(scanner, datagramChannel);

        RequestController controller = new RequestController(nio, new ServerCommandsManager(cities));
        controller.handleRequest();
    }
}
