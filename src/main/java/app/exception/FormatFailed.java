package app.exception;

public class FormatFailed extends FundamentError{

    public FormatFailed(Throwable cause) {

        super(ListErrors.FORMAT_OUTPUT_FAILED, cause.getMessage());

    }

}
