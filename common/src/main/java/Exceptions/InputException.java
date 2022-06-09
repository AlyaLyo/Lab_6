package Exceptions;

/**
 * Ошибка некорректного ввода
 */

public class InputException extends RuntimeException{

    public InputException(String message) { super(message); }

}
