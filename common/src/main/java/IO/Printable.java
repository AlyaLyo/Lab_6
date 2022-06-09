package IO;

import java.io.IOException;

/**
 * Interface print classes.
 */

public interface Printable {

    void println(String string) throws IOException;
    void print(String string) throws Exception;

}
