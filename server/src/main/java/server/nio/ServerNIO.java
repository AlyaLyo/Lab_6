package server.nio;

import IO.Printable;
import IO.Scannable;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class ServerNIO implements Scannable, Printable {

    private ServerPrint serverPrint;
    private ServerScan serverScan;
    private DatagramChannel datagramChannel;

    public ServerNIO(ServerScan serverScan, DatagramChannel datagramChannel) {
        this.serverScan = serverScan;
        this.datagramChannel = datagramChannel;
    }

    @Override
    public void println(String string) throws IOException {
        serverPrint.println(string);
    }

    @Override
    public void print(String string) throws Exception {
        serverPrint.print(string);
    }

    @Override
    public Integer scanInteger() throws Exception {
        return serverScan.scanInteger();
    }

    @Override
    public Double scanDouble() throws Exception {
        return serverScan.scanDouble();
    }

    @Override
    public String scanString() throws IOException {
        String result = serverScan.scanString();
        serverPrint = new ServerPrint(datagramChannel, serverScan.getSocketAddress());
        return result;
    }

    @Override
    public Long scanLong() throws Exception {
        return serverScan.scanLong();
    }

    @Override
    public boolean hasNextLine() {
        return serverScan.hasNextLine();
    }
}
