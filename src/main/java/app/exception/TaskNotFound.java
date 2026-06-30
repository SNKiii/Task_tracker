package app.exception;



public class TaskNotFound extends FundamentError {

    public TaskNotFound(Long id) {

        super(ListErrors.TASK_NOT_FOUND, String.format("Task with ID %d not found", id), id);

    }

    public TaskNotFound(String name) {

        super(ListErrors.TASK_NOT_FOUND, String.format("Task with the name '%s' not found", name), name);

    }



}
