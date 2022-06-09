package IO;

import java.io.IOException;

/**
 * Interface scan classes.
 */

public interface Scannable {

    Integer scanInteger() throws Exception;
    Double scanDouble() throws Exception;
    String scanString() throws IOException;
    Long scanLong() throws Exception;
    boolean hasNextLine();
}
