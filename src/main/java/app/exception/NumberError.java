package app.exception;

public class NumberError extends FundamentError{

    public NumberError(String number, Throwable cause) {

        super(ListErrors.NUMBER_NOT_FORMAT, number, cause);

    }

    public NumberError(Throwable cause) {

        super(ListErrors.NUMBER_NOT_FORMAT, cause);

    }

    public NumberError(String number) {

        super(ListErrors.NUMBER_NOT_FORMAT, number);

    }

}
