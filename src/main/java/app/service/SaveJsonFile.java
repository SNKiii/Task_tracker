package app.service;


import app.model.Task;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveJsonFile {



    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private SaveJsonFile(){}

    public static void appendJson (File file, Object object) throws IOException {

        try(FileWriter writer = new FileWriter(file, true)) {
            mapper.writeValue(writer, object);
            writer.write("\n");
        }

    }

    public static List<Task> readJsonFiles(File file) throws IOException {

        List<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null ) {

                if (!line.trim().isEmpty()) {

                    Task task = mapper.readValue(line, Task.class);
                    tasks.add(task);

                }
            }
        }

        return tasks;
    }

    public static void rewriteJsonFile(File file) throws IOException {

        try (FileWriter writer = new FileWriter(file)){

        } catch (IOException e) {

            throw new IOException();

        }

    }

//    public static void deleteLineById(File file) {
//
//        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
//
//
//
//        }
//
//    }


    public static boolean searchTaskById(File file, String id) throws FileNotFoundException {

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null && !line.trim().isEmpty()) {

                JsonNode idLine = mapper.readTree(line).get("id");

                if (id.equals(idLine.asText())) {

                    return true;

                }

            }

            return false;


        } catch(IOException e) {

            throw new FileNotFoundException(e.getMessage());

        }

    }

}
