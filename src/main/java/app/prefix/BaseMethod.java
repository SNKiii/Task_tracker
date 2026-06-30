package app.prefix;

import app.constants.DataDefaultNumber;
import app.exception.CommandParsingError;
import app.exception.IncorrectDataEntry;
import app.format.UpdateFormat;
import app.service.Console;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class BaseMethod {

    private final static int COUNT_CUCLES = 4;


    private BaseMethod() {}

    public static Map<String, String> whileAddTask() {

        Map<String, String> taskArgument = new HashMap<>();

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        for (int i = 0; i < COUNT_CUCLES; i++){

            switch (i) {

                case 0 -> {

                    System.out.print("name: ");
                    String field = scanner.nextLine();

                    if (field == null || field.isEmpty()) {

                        throw new IncorrectDataEntry("Некорректный ввод названия задачи");

                    }

                    taskArgument.put("name", field);

                }

                case 1 -> {

                    System.out.print("body: ");
                    String field = scanner.nextLine();

                    if (field == null || field.isEmpty()) {

                        throw new IncorrectDataEntry("Некорректный ввод основного тела задачи");

                    }

                    taskArgument.put("body", field);

                }

                case 2 -> {

                    System.out.print("importanceLevel: ");
                    String field = scanner.nextLine();

                    if (field == null || !field.matches("([0-9]|10)")) {

                        throw new IncorrectDataEntry("Некорректный ввод уровня важности задачи");

                    }

                    taskArgument.put("importanceLevel", field);

                }

                case 3 -> {

                    System.out.print("dueDate: ");
                    String field = scanner.nextLine();

                    if (field == null || !field.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {

                        field = DataDefaultNumber.FUTURE_LOCAL_DATE_STRING;

                    }

                    taskArgument.put("dueDate", field);

                }

            }



        }

        return taskArgument;
    }
    public static String updateTask(Console console, String prefix, String id) {

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Long longId;
         try{

             longId = Long.parseLong(id);
        switch (prefix) {

            case "-s" -> {


                System.out.println("Задача " + console.consoleShowNameById(longId) + "имеет на данный момент стадию: " + console.consoleShowStageById(longId)
                        + " Вы уверены, что хотите изменить? \n да/нет");


                while(true) {

                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("нет")) {

                        break;

                    } else if (input.equalsIgnoreCase("да")){

                        System.out.println("Укажите новую стадию -> ");
                        String newStage = scanner.nextLine();

                        console.consoleSetStage(longId, newStage);
                        System.out.println("Стадия выполнения задачи успешно обновлена!");
                        break;
                    }

                }
            }

            case "-dt" -> {

                System.out.println("Задача " + console.consoleShowNameById(longId) + "имеет на данный момент срок выполнения до : "
                        +
                        console.consoleShowDueDateById(longId)
                        +
                        " Вы уверены, что хотите изменить? \n да/нет");


                while(true) {

                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("нет")) {

                        break;

                    } else if (input.equalsIgnoreCase("да")){

                        System.out.println("Укажите новую дату (YYYY-MM-DD) -> ");
                        String newDueDate = scanner.nextLine();

                        if (!newDueDate.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {

                            throw new IncorrectDataEntry("Неверный формат даты");

                        }

                        console.consoleSetDueDate(longId, newDueDate);
                        System.out.println("Срок выполнения задачи успешно обновлен!");
                        break;


                    }

                }

            }

        }
         } catch (NumberFormatException e) {

            throw new CommandParsingError("id указан неверно. Имеет не числовой тип");

         }

         return UpdateFormat.taskFormat(console.getTaskById(longId));

    }

    public static String deleteAllTasks (Console console, String prefix) {

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        switch (prefix) {

            case "-a" -> {

                System.out.println("Вы уверены, что хотите удалить все задачи из временного хранилища?\n"
                        +
                        "Несохраненные данные невозможно будет восстановить\n"
                        +
                        "да/нет ");

                while(true) {

                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("нет")) {

                        break;

                    } else if (input.equalsIgnoreCase("да")){

                        return console.consoleDeleteAll();

                    }

                }

            }

            case "-aR" -> {

                System.out.println("""
                        ПРЕДУПРЕЖДЕНИЕ! Данная команда удалит все когда-либо записанные задачи в памяти.\
                        Прежде чем соглашаться, еще раз подумайте!
                        
                        
                        \
                        Вы уверены, что хотите удалить все задачи из внутреннего хранилища?
                        Удаленные данные невозможно будет восстановить
                        да/нет\s""");

                while(true) {

                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("нет")) {

                        break;

                    } else if (input.equalsIgnoreCase("да")){

                        String outPutLine = console.consoleDeleteAll();
                        console.getJsonManager().turningEmptyFile();
                        System.out.println("Все прошло успешно! Хранилище приложения полностью пустое");
                        return outPutLine;

                    }

                }


            }


        }

        return "Error prefix";

    }


    public static String deleteTaskFromId(Console console, String prefix, Long id) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);



        switch (prefix) {

            case "-i" -> {

                System.out.println("Вы уверены, что хотите удалить эту задачу из временного хранилища?\n"
                        +
                        "Несохраненные данные невозможно будет восстановить\n"
                        +
                        "да/нет ");

                while(true) {

                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("нет")) {

                        break;

                    } else if (input.equalsIgnoreCase("да")){


                        try {
                             return  console.consoleDelete(id);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }

            }

            case "-iR" -> {

                System.out.println("""
                        ПРЕДУПРЕЖДЕНИЕ! Данная команда удалит все данные задачи когда-либо записанные  в памяти.\
                        Прежде чем соглашаться, еще раз подумайте!
                        
                        
                        \
                        Вы уверены, что хотите удалить все данные этой задачи из внутреннего хранилища?
                        Удаленные данные невозможно будет восстановить
                        да/нет\s""");

                while(true) {

                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("нет")) {

                        break;

                    } else if (input.equalsIgnoreCase("да")){

                        String outPutLine = console.consoleDelete(id);
                        console.getJsonManager().startWriterTasks(console);
                        return outPutLine;

                    }

                }


            }


        }
        return "Error delete task";
    }


}
