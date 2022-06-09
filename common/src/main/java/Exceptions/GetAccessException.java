package Exceptions;

/**
 * Ошибка доступа к файлу
 */

public class GetAccessException extends RuntimeException {

    public GetAccessException(String message) { super(message); }

}
