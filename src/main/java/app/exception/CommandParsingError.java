package app.exception;

public class CommandParsingError extends FundamentError{

    public CommandParsingError(ListErrors error, String message, Object... args) {

       super(error, message, args);

    }

    public CommandParsingError(ListErrors error, String message) {

        super(error, message);

    }

    public CommandParsingError(ListErrors error, int size) {

        super(error, size);

    }

}
