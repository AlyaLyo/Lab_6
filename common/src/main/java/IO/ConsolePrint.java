package IO;

/**
 * Класс, отвечающий за вывод в консоль.
 */

public class ConsolePrint implements Printable {

    @Override
    public void println(String string) {
        System.out.println(string);
    }

    @Override
    public void print(String string) {
        System.out.print(string);
    }

}
