package server.nio;

import IO.Scannable;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.rmi.server.ExportException;
import java.util.Iterator;
import java.util.Set;

public class ServerScan implements Scannable {

    private Selector selector;
    private SocketAddress socketAddress;

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public ServerScan (DatagramChannel channel) throws IOException {
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ,null);
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
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                if (key.isReadable()) {
                    DatagramChannel channel = (DatagramChannel) key.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(65536);
                    socketAddress = channel.receive(buffer);
                    return new String(buffer.array()).trim();
                }

            }
        }
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
