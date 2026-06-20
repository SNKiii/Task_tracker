package app.service;

import app.constants.DataDefaultNumber;
import app.eception.IncorrectDataEntry;
import app.eception.TaskNotFound;
import app.format.BaseFormat;
import app.format.DeleteFormat;
import app.model.Task;
import app.param.Param;
import app.repository.TaskRepository;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Console {


    private final TaskRepository taskRepository = new TaskRepository();

    public String consoleAdd(@Param("name") String name, @Param("body") String body,
                             @Param("importanceLevel") String importanceLevel, @Param("dueDate") String dueDate) {

        if (name.isEmpty() || body == null) {

            throw new IncorrectDataEntry("Incorrect data. \" +\n" +
                    "\"Please review the task completion rules again.");

        }

        try {
            Task task = new Task();
            task.setName(name);
            task.setBody(body);

            task.setDueDate(LocalDate.parse(dueDate));

            if (Byte.parseByte(importanceLevel) == 0){

                task.setImportanceLevel((byte) 1);

            } else {

                task.setImportanceLevel(Byte.parseByte(importanceLevel));

            }

            taskRepository.create(task);

            return BaseFormat.taskFormat(task);
        } catch (DateTimeParseException e) {

            throw new IncorrectDataEntry("The date is in the wrong format" + e.getMessage());

        }



    }

    public void consoleAdd(String name, String body, String importanceLevel,
                             LocalDate dueDate, String stage, LocalDate createdAt, Long id) {

        if (name.isEmpty()
                ||
                body == null
                ||
                dueDate == null
                ||
                stage == null || stage.isEmpty()
                ||
                createdAt == null
                ||
                id == null
        ) {

            throw new IncorrectDataEntry("Invalid data in file");

        }

        try {
            Task task = new Task();
            task.setName(name);
            task.setBody(body);

            task.setDueDate(dueDate);
            task.setCreatedAt(createdAt);
            task.setStage(stage);
            task.setId(id);

            if (Byte.parseByte(importanceLevel) == 0){

                task.setImportanceLevel((byte) 1);

            } else {

                task.setImportanceLevel(Byte.parseByte(importanceLevel));

            }

            taskRepository.create(task);
        } catch (DateTimeParseException e) {

            throw new IncorrectDataEntry("The date from the file is in the wrong format.");

        }



    }

    public List<Task> consoleGetAllTasks() {

        return taskRepository.show();

    }

    public String consoleShow() {

        return consoleShow(DataDefaultNumber.DEFAULT_NUMBER_SIZE_TASKS);

    }

    public String consoleShow(Integer sizeTasks) {



        if (sizeTasks == null || sizeTasks <= 0) {

            sizeTasks = DataDefaultNumber.DEFAULT_NUMBER_SIZE_TASKS;

        }


        StringBuilder result = new StringBuilder();

        try {
            var tasks = taskRepository.show();

            for (int i = 0; i < sizeTasks && i < tasks.size(); i++) {

                result.append(BaseFormat.taskFormat(tasks.get(i)));
                result.append("\n");

            }

            return result.toString();

        } catch (TaskNotFound e) {

            return "No tasks found";

        }
    }

    public String consoleShowById(Long id) {

        if (id == null || id <= 0) {

            throw new IncorrectDataEntry("Invalid input");

        }

        try {

            return BaseFormat.taskFormat(taskRepository.getTaskById(id));

        } catch (TaskNotFound e) {

            return "Error | | | " + e.getMessage();

        }

    }

    public  String consoleShowByName(String name) {

        if (name == null || name.isEmpty()) {

            throw new IncorrectDataEntry("Invalid input");

        }


        try {

            return BaseFormat.taskFormat(taskRepository.getTaskByName(name));

        } catch (TaskNotFound e) {

            return "Error | | | " + e.getMessage();

        }


    }

    public String consoleShowImportanceTask() {

        byte importanceLevel = DataDefaultNumber.DEFAULT_IMPORTANCE_LEVEL_TASKS;
        return consoleShowImportanceTask(String.valueOf(importanceLevel));

    }

    public String consoleShowImportanceTask(String fixedImportanceLevel) {

        var tasks = taskRepository.showImportant(Byte.parseByte(fixedImportanceLevel));

        if (tasks == null || tasks.isEmpty()) {

            return "You don't have any important tasks.";

        } else {

            return tasks.toString();

        }

    }

    public String consoleShowStageById(Long id) {

        if (id == null) {

            throw new IncorrectDataEntry("Empty id input");

        }

        var task = taskRepository.getTaskById(id);

        return task.getStage();

    }

    public String consoleShowNameById(Long id) {

        if (id == null) {

            throw new IncorrectDataEntry("Пустой ввод id");

        }

        var task = taskRepository.getTaskById(id);

        return task.getName();

    }

    public  LocalDate consoleShowDueDateById(Long id) {

        if (id == null) {

            throw new IncorrectDataEntry("Пустой ввод id");

        }

        var task = taskRepository.getTaskById(id);

        return task.getDueDate();

    }

    public  String consoleSetStage(Long id, String stage) {

        if (id == null || id <= 0 || stage == null) {

            throw new IncorrectDataEntry();

        }
        try {

            var task = taskRepository.getTaskById(id);
            return taskRepository.completedMark(task, stage).toString();

        } catch (TaskNotFound e) {

            return e.getMessage();

        }

    }

    public String consoleSetDueDate(Long id, String dueDate) {

        if (id == null || id <= 0 || dueDate == null) {

            throw new IncorrectDataEntry();

        }
        try {

            var task = taskRepository.getTaskById(id);
            return taskRepository.setDateComplate(task, LocalDate.parse(dueDate)).toString();

        } catch (TaskNotFound e) {

            return e.getMessage();

        }

    }

    public String consoleDelete(Long id) throws FileNotFoundException {

        var task = taskRepository.getTaskById(id);
        try {
            taskRepository.delete(id);
            return DeleteFormat.taskFormatToOne(task)
                    +
                    "\n"
                    +
                    "The task has been successfully deleted!\n" +
                    "+\n" +
                    "\"Uncompleted tasks remain:" + taskRepository.showSizeTask();
        } catch (TaskNotFound e) {
            return  "The task cannot be deleted due to the following reason:: " + e.getMessage();
        }
    }

    public String consoleDeleteAll() {
        taskRepository.deleteAll();
        return  DeleteFormat.taskFormat(getAllNameTasks());
    }

    public Integer getSizeTasks() {

        return taskRepository.showSizeTask();

    }

    public Task getTaskById(Long id) {

        return taskRepository.getTaskById(id);

    }

    public List<String> getAllNameTasks() {

        List<String> nameTasks = new ArrayList<>();
        var tasks = taskRepository.show();

        for (var task : tasks) {

            nameTasks.add(taskRepository.getTaskName(task));

        }

        return nameTasks;

    }

}
