package Commands;

import java.io.IOException;

/**
 * Interface commands.
 */

public interface Commands {

    /**
     * Метод, запускающий команду.
     */

    void execute() throws IOException, Exception;

    static int[] argumentsAmount() {
        return new int[0];
    }

}
