package server.nio;

import IO.Printable;

import java.io.IOException;

public class SelfPrint implements Printable {

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void println(String message) throws IOException {
        stringBuilder.append(message).append("\n");
    }

    @Override
    public void print(String message) throws IOException {
        stringBuilder.append(message);
    }

    public String getString() {
        return stringBuilder.toString();
    }
}
