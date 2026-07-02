package app.repository;

import app.exception.TaskNotFound;
import app.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private ArrayList<Task> tasks;

    public TaskRepository() {
        this.tasks = new ArrayList<>();
    }

    public Task create(Task task) {

        tasks.add(task);
        return task;

    }

    public ArrayList<Task> show() {

        if (tasks.isEmpty()) {

            throw new TaskNotFound();

        }
        return tasks;
    }

    public List<Task> getTasks(int quantity) {

        if (tasks.isEmpty()) {

            throw new TaskNotFound();

        }

        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {

            taskList.add(tasks.get(i));

        }

        return taskList;

    }

    public Task getTaskById(Long id) {

        return tasks.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(id));

    }

    public Task getTaskByName(String name) {

        return tasks.stream()
                .filter(v -> v.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(name));
    }

    public void delete(Long id) {
        tasks.remove(tasks.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(id)));
    }

    public void deleteAll() {
        tasks.clear();
    }

    public Task completedMark(Task task, String stage) {
        task.setStage(stage);
        return task;
    }

    public Task setDateComplate(Task task, LocalDate dueDate) {
        task.setDueDate(dueDate);
        return task;
    }

    public List<Task> showImportant(byte level)
    {
        return tasks.stream()
                            .filter(v -> v.getImportanceLevel() == level)
                            .toList();

    }

    public Integer showSizeTask() {
        return tasks.size();
    }

    public String getTaskName(Task task) {

        return task.getName();

    }

}
