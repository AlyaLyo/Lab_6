package client.io;

import IO.Printable;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class ClientPrint implements Printable {

    private DatagramSocket socket;
    private int port;

    public ClientPrint(DatagramSocket socket, int port) {
        this.socket = socket;
        this.port = port;
    }

    @Override
    public void println(String string) throws IOException {
        byte[] bytes = string.getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, new InetSocketAddress("localhost", port));
        socket.send(packet);
    }

    @Override
    public void print(String string) throws Exception {
        throw new Exception();
    }
}
