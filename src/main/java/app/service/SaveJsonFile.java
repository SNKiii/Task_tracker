package app.service;


import app.model.Task;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SaveJsonFile {



    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private SaveJsonFile(){}

    public static void appendJson (File file, List<Task> tasks) throws IOException {

        try {
            List<String> lines = Files.readAllLines(file.toPath());
            List<String> updaterLine = new ArrayList<>();

            Map<Long, Task> taskMap = tasks.stream()
                    .collect(Collectors.toMap(Task::getId, Function.identity()));
            boolean check = false;

            for (String line : lines) {

                if (line.trim().isEmpty()) {

                    updaterLine.add(line);
                    continue;

                }


                JsonNode node = mapper.readTree(line);

                if (node.has("id") ) {
                    long id = node.get("id").asLong();
                    Task newTask = taskMap.get(id);

                    if (newTask != null) {

                        JsonNode taskNode = mapper.valueToTree(newTask);
                        ObjectNode mergedNode = (ObjectNode) node;

                        taskNode.fields().forEachRemaining(entry -> {
                            mergedNode.set(entry.getKey(), entry.getValue());
                        });

                        line = mapper.writeValueAsString(mergedNode);
                        check = true;

                        taskMap.remove(id);

                    }
                }

                updaterLine.add(line);

            }

            for (Task newTask : taskMap.values()) {
                String jsonLine = mapper.writeValueAsString(newTask);
                updaterLine.add(jsonLine);
                check = true;
            }

            if (check) {

                Files.write(file.toPath(), updaterLine, StandardCharsets.UTF_8);

            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to update task", e);
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

//    public static boolean searchIdSearch() {
//
//
//
//    }


//    public static void updateJson(Task task, File file) {
//
//
//        try {
//            List<String> lines = Files.readAllLines(file.toPath());
//            List<String> updaterLine = new ArrayList<>();
//
//
//            boolean check = false;
//
//            for (String line : lines) {
//
//                if (line.trim().isEmpty()) {
//
//                    updaterLine.add(line);
//                    continue;
//
//                }
//
//
//                JsonNode node = mapper.readTree(line);
//
//                if (node.has("id") && node.get("id").asLong() == task.getId()) {
//
//                    JsonNode taskNode = mapper.valueToTree(task);
//                    ObjectNode mergedNode = (ObjectNode) node;
//
//                    taskNode.fields().forEachRemaining(entry -> {
//                        mergedNode.set(entry.getKey(), entry.getValue());
//                    });
//
//                    line = mapper.writeValueAsString(mergedNode);
//                    check = true;
//
//
//                }
//
//                updaterLine.add(line);
//
//            }
//
//            if (!check) {
//
//                String jsonLine = mapper.writeValueAsString(task);
//                updaterLine.add(jsonLine);
//
//            }
//
//            Files.write(file.toPath(), updaterLine, StandardCharsets.UTF_8);
//
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to update task", e);
//        }
//
//    }


}
