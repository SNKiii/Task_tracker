package app.exception;

public class TaskNotDeleted extends FundamentError{

    public TaskNotDeleted(Throwable cause) {

        super(ListErrors.TASK_NOT_DELETED, String.format("Failed to delete the task due to '%s'", cause.getMessage()), cause);

    }

}
