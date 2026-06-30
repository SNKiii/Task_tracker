package app.exception;

public class CommandNotFound extends CommandParsingError{

    public CommandNotFound(String message) {

        super(ListErrors.COMMAND_NOT_FOUND, message);

    }

}
