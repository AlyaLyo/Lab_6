package client;

import Exceptions.MyRuntimeException;
import IO.*;
import client.Client;
import client.io.ClientPrint;
import client.io.ClientScan;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import deserializes.ResponseDeserializer;
import requests.InitialRequest;
import requests.Request;
import responses.Response;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.Scanner;

public class ClientMain {

    final static int port = 6006;
    final static String host = "localhost";

    public static void main(String[] args) throws Exception {

        Printable printable = new ConsolePrint();
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.setSoTimeout(2500);
        ClientScan clientScan = new ClientScan(datagramSocket);
        ClientPrint clientPrint = new ClientPrint(datagramSocket, port);

        Client client = new Client(clientScan, clientPrint);

        Random random = new Random();

        waitForConnection(random, clientPrint, clientScan);

        Scannable scannable = new ConsoleScan();

        while (true) {
            printable.print("Enter command: ");
            String line = scannable.scanString();
            try {
                client.request(line, new ConsoleScan(), new ConsolePrint(), true);
            }catch (MyRuntimeException myRuntimeException) {
                waitForConnection(random, clientPrint, clientScan);
            } catch (Exception e){
                System.out.println("Something is wrong: " + e.getMessage());
            }
        }
    }

    private static void serverInit(Random random, ClientPrint clientPrint, ClientScan clientScan) throws Exception {
        int number = random.nextInt();

        Request request = new Request(new InitialRequest(number));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new SimpleModule().addDeserializer(Response.class, new ResponseDeserializer()));
        String requestString = objectMapper.writeValueAsString(request);

        clientPrint.println(requestString);

        String responseString = clientScan.scanString();
        Response response = objectMapper.readValue(responseString, Response.class);

        if (Integer.parseInt(String.valueOf(response.content)) != number) {
            throw new Exception("not connected");
        }
    }

    private static void waitForConnection(Random random, ClientPrint clientPrint, ClientScan clientScan) {
        while (true) {
            System.out.println("connecting...");
            try {
                serverInit(random, clientPrint, clientScan);
            } catch (Throwable throwable) {
                continue;
            }
            break;
        }

        System.out.println("Connection!");
    }
}
