package client.io;

import Exceptions.MyRuntimeException;
import IO.Scannable;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

public class ClientScan implements Scannable {

    private DatagramSocket socket;

    public ClientScan(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public Integer scanInteger() throws Exception {
        throw new Exception();
    }

    @Override
    public Double scanDouble() throws Exception {
        throw new Exception();
    }

    @Override
    public String scanString() throws IOException {
        byte[] bytes = new byte[65536];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 65536);
        try {
            socket.receive(datagramPacket);
        } catch (Exception e){
            throw new MyRuntimeException();
        }
        return new String(datagramPacket.getData()).trim();
    }

    @Override
    public Long scanLong() throws Exception {
        throw new Exception();
    }

    @Override
    public boolean hasNextLine() {
        return true;
    }
}
