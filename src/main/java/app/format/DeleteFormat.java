package app.format;


import app.model.Task;
import app.service.JsonManager;

import java.io.FileNotFoundException;
import java.util.List;

public class DeleteFormat extends BaseFormat{

    public static String taskFormat(List<String> nameTasks) {

        StringBuilder stringNameDeleteTask = new StringBuilder();

        for (var nameTask : nameTasks) {

            stringNameDeleteTask.append("Deleting a task named -> ");
            stringNameDeleteTask.append(nameTask);
            stringNameDeleteTask.append(" | ");
            stringNameDeleteTask.append("Permanent deletion - successful");
            stringNameDeleteTask.append("\n");

        }

        return stringNameDeleteTask.toString();


    }

    public static String taskFormatToOne(Task task) throws FileNotFoundException {

        StringBuilder stringNameDeleteTask = new StringBuilder();

        stringNameDeleteTask.append("Deleting a task -> ");
        stringNameDeleteTask.append(task.getName());
        stringNameDeleteTask.append("\n");

        if (JsonManager.checkingAvailability(task.getId())) {

            stringNameDeleteTask.append("The task data is still saved in the storage.");
            stringNameDeleteTask.append("/n");
            stringNameDeleteTask.append("Task id with repository ->");
            stringNameDeleteTask.append(task.getId());

        } else {

            stringNameDeleteTask.append("These tasks are not stored in external storage.");

        }

        return stringNameDeleteTask.toString();

    }


}
