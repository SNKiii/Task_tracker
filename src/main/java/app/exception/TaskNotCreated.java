package app.exception;

public class TaskNotCreated extends FundamentError{


    public TaskNotCreated(Throwable cause) {

        super(ListErrors.TASK_NOT_CREATED, String.format("The task was not created because of '%s'", cause.getMessage()), cause);

    }
}
