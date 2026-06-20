package app.eception;



public class TaskNotFound extends RuntimeException {

    public TaskNotFound (String massage) {

        super(massage);

    }

    public  TaskNotFound () {
    }

}
