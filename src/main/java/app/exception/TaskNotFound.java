package app.exception;


import app.repository.TaskRepository;

public class TaskNotFound extends FundamentError {

    public TaskNotFound(Long id) {

        super(ListErrors.TASK_NOT_FOUND, String.format("Task with ID %d not found", id), id);

    }

    public TaskNotFound(String name) {

        super(ListErrors.TASK_NOT_FOUND, String.format("Task with the name '%s' not found", name), name);

    }

    public TaskNotFound() {

        super(ListErrors.TASK_NOT_FOUND, "The storage is empty.");

    }



}
