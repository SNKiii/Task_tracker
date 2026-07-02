package app.exception;

public class CommandNotArgument extends CommandParsingError{


    public CommandNotArgument(String message, Long size) {

        super(ListErrors.COMMAND_WITHOUT_REQUIRED_ARGUMENTS, message, size);

    }

    public CommandNotArgument(int size) {

        super(ListErrors.COMMAND_WITHOUT_REQUIRED_ARGUMENTS, size);

    }

}
