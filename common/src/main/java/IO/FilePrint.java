package IO;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Класс, отвечающий за вывод в файл.
 */

public class FilePrint implements Printable {

    private BufferedWriter writer;

    public FilePrint(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void println(String string) throws IOException {
        writer.write(string);
        writer.newLine();
    }

    @Override
    public void print(String string) throws IOException {
        writer.write(string);
    }

}
