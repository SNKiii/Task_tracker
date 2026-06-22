package app.parser;

import app.constants.DataDefaultNumber;
import app.eception.CommandParsingError;
import app.eception.IncorrectDataEntry;
import app.param.StringParam;
import app.prefix.BaseMethod;
import app.service.Console;

import java.lang.reflect.Method;
import java.lang.runtime.SwitchBootstraps;
import java.sql.Struct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComandParser {


    public static String parser(String comand, Console console) throws NoSuchMethodException {

        String outPutLine = "";

        Pattern pattern = Pattern.compile("^/(add|show|update|delete)(?:\\s|$)");
        Matcher matcher = pattern.matcher(comand.trim());

        if (!matcher.matches()) {

            throw new CommandParsingError("Ошибка в команде. Перепроверьте и попытайтесь снова");

        }

        String[] string = comand.split(" ");

        switch (string[0]) {

            case "/add" -> {

                String name = "";
                String body = "";
                String importanceLevel = "";
                String dueDate = "";

                Method method = Console.class.getMethod("consoleAdd", String.class, String.class, String.class, String.class);

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

                            throw new IncorrectDataEntry("Неверный ввод команды. Недостаточно данных для работы");

                        }

                        try {
                            Long id = Long.parseLong(string[2]);
                            if (id <= 0) {

                                throw new IncorrectDataEntry("Id неккоректный ");

                            }
                            return console.consoleShowById(id);

                        } catch (NumberFormatException e) {

                            throw new IncorrectDataEntry("Неверная запись id. Используйте только числа [0-9]");

                        }

                    }

                    case "-in" -> {

                        if (string.length != 3 && string[2] != null) {

                            throw new IncorrectDataEntry("Неверный ввод команды. Недостаточно данных для работы");

                        }

                        outPutLine = console.consoleShowByName(string[2]);
                        return outPutLine;

                    }

                    case "-l" ->  console.consoleShowImportanceTask();


                    case "-li" -> {

                        if (string.length != 3 && string[2] != null) {

                            throw new IncorrectDataEntry("Неверный ввод команды. Недостаточно данных для работы");

                        }

                        outPutLine = console.consoleShowImportanceTask(string[2]);
                        return outPutLine;


                    }

                    default ->  console.consoleShow(Integer.MAX_VALUE);

                }
            }

            case  "/update" -> {

                if (string.length <= 3 || string[2] == null || string[2].isEmpty()
                        ||
                        string[3] == null || string[3].isEmpty()) {

                    throw new IncorrectDataEntry("Неверный ввод команды. Недостаточно данных для работы");

                }

                outPutLine = BaseMethod.updateTask(console, string[2], string[3]);

                return outPutLine;
            }

            case "/delete" -> {
                if (string.length == 2) {

                    if (string[1] == null || string[1].isEmpty()) {

                        throw new IncorrectDataEntry("Неверный ввод команды. Недостаточно данных для работы");

                    }

                    return BaseMethod.deleteAllTasks(console, string[1]);

                } else if (string.length > 2) {

                    if (string.length <= 3 || string[2] == null || string[2].isEmpty()
                            ||
                            string[3] == null || string[3].isEmpty()) {

                        throw new IncorrectDataEntry("Неверный ввод команды. Недостаточно данных для работы");

                    }

                    try {

                        return BaseMethod.deleteTaskFromId(console, string[1], Long.parseLong(string[2]));

                    } catch (NumberFormatException e) {

                        throw new IncorrectDataEntry("Неверная запись id. Используйте только числа [0-9]");

                    }

                }


            }

        }

        return "";

    }



}
