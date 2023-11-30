package bg.softuni.quizkids.exceptions;

public class QuestionNotFoundException extends IllegalArgumentException {
    public QuestionNotFoundException(String msg) {
        super(msg);
    }

    public QuestionNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
