package server.nio;

import IO.Printable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

public class ServerPrint implements Printable {

    private DatagramChannel channel;
    private SocketAddress socketAddress;

    public ServerPrint(DatagramChannel channel, SocketAddress socketAddress) {
        this.channel = channel;
        this.socketAddress = socketAddress;
    }

    @Override
    public void println(String string) throws IOException {
        byte[] bytes = string.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        channel.send(buffer, socketAddress);
    }

    @Override
    public void print(String string) throws Exception {
        throw new Exception();
    }
}
