package IO;

import java.io.FileReader;
import java.io.IOException;

/**
 * Класс, отвечающий за ввод с файла.
 */

public class FileScan implements Scannable {

    private FileReader reader;
    private boolean eof = false;

    public FileScan (String fileName) throws IOException {
        reader = new FileReader(fileName);
        if (reader.read() == -1) {
            this.eof = true;
        }
        reader = new FileReader(fileName);
    }

    @Override
    public Integer scanInteger() throws IOException {
        StringBuilder string = new StringBuilder();
        if (hasNextLine()) {
            while (true) {
                int ch = reader.read();
                char chr = (char) ch;
                if (chr == '\n' || chr == ' ' || ch == -1) {
                    if (ch == -1) {
                        eof = true;
                    }
                    break;
                }
                string.append(chr);
            }
        }
        return Integer.parseInt(string.toString().replace("\r",""));
    }

    @Override
    public Double scanDouble() throws IOException {
            StringBuilder string = new StringBuilder();
            if (hasNextLine()) {
                while (true) {
                    int ch = reader.read();
                    char chr = (char) ch;
                    if (chr == '\n' || chr == ' ' || ch == -1) {
                        if (ch == -1) {
                            eof = true;
                        }
                        break;
                    }
                    string.append(chr);
                }
            }
            return Double.parseDouble(string.toString().replace("\r",""));
    }

    @Override
    public String scanString() throws IOException {
        StringBuilder string = new StringBuilder();
        if (hasNextLine()) {
            while (true) {
                int ch = reader.read();
                char chr = (char) ch;
                if (chr == '\n' || ch == -1) {
                    if (ch == -1) {
                        eof = true;
                    }
                    break;
                }
                string.append(chr);
            }
        }
        return string.toString().replace("\r","");
    }

    @Override
    public Long scanLong() throws IOException {
        StringBuilder string = new StringBuilder();
        if (hasNextLine()) {
            while (true) {
                int ch = reader.read();
                char chr = (char) ch;
                if (chr == '\n' || chr == '\u0020' || ch == -1) {
                    if (ch == -1) {
                        eof = true;
                    }
                    break;
                }
                string.append(chr);
            }
        }
        return Long.parseLong(string.toString().replace("\r",""));
    }

    @Override
    public boolean hasNextLine() {
        return !eof;
    }
}
