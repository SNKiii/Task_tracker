package app.exception;

public class SystemError extends FundamentError{

    public SystemError(Throwable cause) {

        super(ListErrors.SYSTEM_ERROR, cause.getLocalizedMessage());

    }

}
