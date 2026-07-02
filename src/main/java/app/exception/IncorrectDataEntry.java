package app.exception;

public class IncorrectDataEntry  extends  FundamentError{

   public IncorrectDataEntry(String message) {

       super(ListErrors.USER_INPUT_ERROR, message);

   }

}
