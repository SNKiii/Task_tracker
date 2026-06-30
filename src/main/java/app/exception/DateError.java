package app.exception;

public class DateError extends FundamentError{

    public DateError(String date, Throwable cause) {

        super(ListErrors.DATE_NOT_FORMAT, date, cause);

    }

    public DateError(Throwable cause) {

        super(ListErrors.DATE_NOT_FORMAT, cause);

    }

    public DateError(String date) {

        super(ListErrors.DATE_NOT_FORMAT, date);

    }

}
