package app.eception;

public class CommandParsingError extends RuntimeException{

    public CommandParsingError(String message) {

       super(message);

    }

}
