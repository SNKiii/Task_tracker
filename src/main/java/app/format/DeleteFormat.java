package app.format;


import app.exception.FormatFailed;
import app.exception.IncorrectDataEntry;
import app.exception.TaskNotDeleted;
import app.model.Task;
import app.service.JsonManager;

import java.io.FileNotFoundException;
import java.util.List;

public class DeleteFormat extends BaseFormat{

    public static String taskFormat(List<String> nameTasks) {

        StringBuilder stringNameDeleteTask = new StringBuilder();
        stringNameDeleteTask.append("\n");
        for (var nameTask : nameTasks) {

            stringNameDeleteTask.append("Deleting a task named -> ");
            stringNameDeleteTask.append(nameTask);
            stringNameDeleteTask.append(" | ");
            stringNameDeleteTask.append("Permanent deletion - successful");
            stringNameDeleteTask.append("\n");

        }

        stringNameDeleteTask.append("\n");

        return stringNameDeleteTask.toString();


    }

    public static String taskFormatToOne(Task task, JsonManager jsonManager)  {

        StringBuilder stringNameDeleteTask = new StringBuilder();

        stringNameDeleteTask.append("\nDeleting a task -> ");
        stringNameDeleteTask.append(task.getName());
        stringNameDeleteTask.append("\n");

        try {
            if (jsonManager.checkingAvailability(task.getId())) {

                stringNameDeleteTask.append("The task data is still saved in the storage.");
                stringNameDeleteTask.append("/n");
                stringNameDeleteTask.append("Task id with repository ->");
                stringNameDeleteTask.append(task.getId());
                stringNameDeleteTask.append("\n");

            } else {

                stringNameDeleteTask.append("These tasks are not stored in external storage.");

            }
        } catch (FileNotFoundException e) {

            throw new FormatFailed(e);

        }

        return stringNameDeleteTask.toString();

    }


}
