package bg.softuni.quizkids.exceptions;

public class QuestionNotFoundException extends IllegalArgumentException {

    public QuestionNotFoundException(String msg) {
        super(msg);
    }
}
