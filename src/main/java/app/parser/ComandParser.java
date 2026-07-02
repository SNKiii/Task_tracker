package app.parser;

import app.constants.ConstantHandler;
import app.exception.*;
import app.param.StringParam;
import app.prefix.BaseMethod;
import app.service.Console;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComandParser {


    public static String parser(String comand, Console console)  {

        String outPutLine = "";

        Pattern pattern = Pattern.compile("^/(add|show|update|delete|com|exit)");
        Matcher matcher = pattern.matcher(comand.trim());

        if (!matcher.find()) {

            throw new CommandNotFound("Invalid command");

        }

        String[] string = comand.split(" ");

        switch (string[0]) {

            case "/add" -> {

                String name = "";
                String body = "";
                String importanceLevel = "";
                String dueDate = "";

                Method method = null;

                try {

                    method = Console.class.getMethod("consoleAdd", String.class, String.class, String.class, String.class);

                } catch (NoSuchMethodException e) {

                    throw new SystemError(e);

                }

                var nameMethods = StringParam.getParams(method, 4);
                var inputUser = BaseMethod.whileAddTask();

                for (int i = 0; i < nameMethods.size(); i++) {

                    switch (nameMethods.get(i)) {

                        case "name" -> name = inputUser.get(nameMethods.get(i));

                        case "body" -> body = inputUser.get(nameMethods.get(i));

                        case "importanceLevel" -> importanceLevel =  inputUser.get(nameMethods.get(i));

                        case "dueDate" -> dueDate = inputUser.get(nameMethods.get(i));

                    }

                }

                outPutLine = console.consoleAdd(name, body, importanceLevel, dueDate);
                return outPutLine;


            }

            case "/show" -> {

                if (console.getSizeTasks() == 0) {

                    System.out.println("There are no tasks yet");
                    return outPutLine;

                } else if (string.length == 1) {

                    return  console.consoleShow();

                }


                switch (string[1]) {

                    case "-a" -> console.consoleShow(Integer.MAX_VALUE);

                    case "-i" -> {

                        if (string.length != 3 || string[2] == null) {

                            throw new CommandNotArgument(string.length);

                        }

                        try {
                            Long id = Long.parseLong(string[2]);
                            if (id <= 0) {

                                throw new IncorrectDataEntry("Invalid ID format");

                            }
                            return console.consoleShowById(id);

                        } catch (NumberFormatException e) {

                            throw new NumberError("Invalid number format", e);

                        }

                    }

                    case "-in" -> {

                        if (string.length < 3 || string[2] == null) {

                            throw new CommandNotArgument(string.length);

                        }

                        StringBuilder partNameTask = new StringBuilder();
                        int count = 0;
                        for (var partName : string) {

                            if (count < 2) {

                                count++;
                                continue;

                            }

                            partNameTask.append(partName);
                            partNameTask.append(" ");

                        }

                        outPutLine = console.consoleShowByName(partNameTask.toString().trim());
                        return outPutLine;

                    }

                    case "-l" ->  console.consoleShowImportanceTask();


                    case "-li" -> {

                        if (string.length != 3 && string[2] != null) {

                            throw new CommandNotArgument(string.length);

                        }

                        outPutLine = console.consoleShowImportanceTask(string[2]);
                        return outPutLine;


                    }

                    default ->  console.consoleShow(Integer.MAX_VALUE);

                }
            }

            case  "/update" -> {

                if (string.length != 3|| string[1] == null || string[1].isEmpty()
                        ||
                        string[2] == null || string[2].isEmpty()) {

                    throw new TaskNotUpdated(new IncorrectDataEntry("Invalid command input. Insufficient data to proceed"));

                }

                outPutLine = BaseMethod.updateTask(console, string[1], string[2]);

                return outPutLine;
            }

            case "/delete" -> {
                if (string.length == 2) {

                    if (string[1] == null || string[1].isEmpty()) {

                        throw new TaskNotDeleted(new IncorrectDataEntry("Invalid command input. Insufficient data to proceed"));

                    }

                    return BaseMethod.deleteAllTasks(console, string[1]);

                } else if (string.length > 2) {

                    if (string.length <= 3 || string[2] == null || string[2].isEmpty()
                            ||
                            string[3] == null || string[3].isEmpty()) {

                        throw new TaskNotDeleted(new IncorrectDataEntry("Invalid command input. Insufficient data to proceed"));

                    }

                    try {

                        return BaseMethod.deleteTaskFromId(console, string[1], Long.parseLong(string[2]));

                    } catch (NumberFormatException e) {

                        throw new TaskNotDeleted(new NumberError("Invalid ID format. Use only numbers [0-9]"));

                    } catch (FileNotFoundException e) {

                        throw new RuntimeException(e);

                    }

                }


            }

            case "/com" -> ConstantHandler.getStartMenu();

        }

        return "";

    }



}