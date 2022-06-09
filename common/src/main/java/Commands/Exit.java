package Commands;

/**
 * Завершает работу программы.
 */

public class Exit implements Commands{

    /**
     * Метод, запускающий выполнение команды.
     */

    @Override
    public void execute() {
        System.exit(0);
    }

    /**
     * @return Command name.
     */

    public static String name() {
        return "exit";
    }

    /**
     * @return Command information.
     */

    public static Information getInfo() throws Exception {
        return new Information(0,0,false,false, null, null);
    }

}
