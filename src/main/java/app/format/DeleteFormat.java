package app.format;

import app.model.Task;

import java.util.List;

public class DeleteFormat extends BaseFormat{

    public static String taskFormat(List<String> nameTasks) {

        StringBuilder stringNameDeleteTask = new StringBuilder();

        for (var nameTask : nameTasks) {

            stringNameDeleteTask.append("Deleting a task named -> ");
            stringNameDeleteTask.append(nameTask);
            stringNameDeleteTask.append(" | ");
            stringNameDeleteTask.append("Permanent deletion - successful");

        }

        return stringNameDeleteTask.toString();


    }

}
