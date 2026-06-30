package app.exception;

public class IncorrectDataEntry  extends  FundamentError{

   public IncorrectDataEntry(Throwable cause) {

       super(ListErrors.USER_INPUT_ERROR, cause);

   }

}
