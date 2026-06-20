package app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class JsonManager {

    private final static Path JSON_FILE_PATH = Path.of("src/main/resources/json/Tasks.json");
    
    private final ObjectMapper mapper;

    public JsonManager(){

        this.mapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);

    }

    public static void startWriterTasks (Console console) {

        try {

            File file = JSON_FILE_PATH.toFile();
            var tasks = SaveJsonFile.readJsonFiles(file);

            for (var task : tasks) {

                console.consoleAdd(task.getName(), task.getBody(), String.valueOf(task.getImportanceLevel()),
                        task.getDueDate(), task.getStage(), task.getCreatedAt(), task.getId());

            }

            if(tasks.isEmpty()) {

                System.out.println("Запуск прошел успешно, но задач пока не найдено");

            }

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

    }

    public static  void endWriterTasks (Console console) {

        try {

            File file = JSON_FILE_PATH.toFile();

            var tasks = console.consoleGetAllTasks();

            for (var task : tasks) {

                SaveJsonFile.appendJson(file, task);

            }

        } catch (IOException e) {

            throw new RuntimeException("При записи задач в файл произошла непредвиденная ошибка");

        }

    }

    public static void turningEmptyFile() {

        try {

            SaveJsonFile.rewriteJsonFile(JSON_FILE_PATH.toFile());

        } catch (IOException e) {

            throw new RuntimeException("При удалении содержимого в файле произошла непредвиденная ошибка");

        }

    }

    public  static void deleteLineJson(Console console) {

        //удаление строки с задачей определенного id


    }
}
