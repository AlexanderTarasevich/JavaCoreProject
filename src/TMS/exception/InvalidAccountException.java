package TMS.exception;

public class InvalidAccountException extends Exception {
    public InvalidAccountException(String massage) {
        super(massage);
    }
}