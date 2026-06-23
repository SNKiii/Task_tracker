package app.service;

import app.eception.IncorrectDataEntry;
import app.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileNotFoundException;
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

                System.out.println("The launch was successful, but no tasks were found yet.");

            }

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

    }

    public static  void endWriterTasks (Console console) {

        try {

            File file = JSON_FILE_PATH.toFile();
            var tasks = console.consoleGetAllTasks();
            SaveJsonFile.appendJson(file, tasks);


        } catch (IOException e) {

            throw new RuntimeException("An unexpected error occurred while writing tasks to file.");

        }

    }

    public static void turningEmptyFile() {

        try {

            SaveJsonFile.rewriteJsonFile(JSON_FILE_PATH.toFile());

        } catch (IOException e) {

            throw new RuntimeException("An unexpected error occurred while deleting the contents of the file.");

        }

    }

//    public  static void deleteLineJson(Console console) {
//
//        //удаление строки с задачей определенного id
//
//
//    }


    public static boolean checkingAvailability(Long id) throws FileNotFoundException {

        if(id == null) {

            throw new IncorrectDataEntry("Invalid task entry");

        }

        String idStr = String.valueOf(id);

        return SaveJsonFile.searchTaskById(JSON_FILE_PATH.toFile(), idStr.trim());

    }

//    public static String updateTask(Console console) {
//
//
//
//    }
}
