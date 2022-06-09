package IO;

import java.util.Scanner;

/**
 * Класс, отвечающий за ввод с консоли.
 */

public class ConsoleScan implements Scannable {

    private Scanner scanner;

    public ConsoleScan () {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Integer scanInteger() {
        if (hasNextLine()) {
            return scanner.nextInt();
        }
        return null;
    }

    @Override
    public Double scanDouble() {
        if (hasNextLine()) {
            return scanner.nextDouble();
        }
        return null;
    }

    @Override
    public String scanString() {
        if (hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    @Override
    public Long scanLong() {
        if (hasNextLine()) {
            return scanner.nextLong();
        }
        return null;
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

}
