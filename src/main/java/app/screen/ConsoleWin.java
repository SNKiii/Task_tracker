package app.screen;

import app.constants.DataPathTextFile;
import app.parser.ComandParser;
import app.service.Console;
import app.service.JsonManager;

import java.io.File;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleWin {

    private static final Console console = new Console();

    public static void startWindow() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        int count = 0;
        while(count == 0) {

            ImageToAscii.printFromFile(DataPathTextFile.PATH_FOR_IMAGE, 185);

            Path path = DataPathTextFile.START_MENU;

            try{
                Files.lines(path, StandardCharsets.UTF_8).forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String input = scanner.nextLine().trim();

            switch (input) {

                case "/start" -> {

                    JsonManager.startWriterTasks(console);

                    try{
                        Files.lines(DataPathTextFile.BASE_COMAND, StandardCharsets.UTF_8).forEach(System.out::println);
                    } catch (IOException e) {
                        System.out.println("Что-то пошло не так: \n" + e.getMessage());
                    }

                    String inputCommand = scanner.nextLine().trim();
                    if (inputCommand.isEmpty()) {

                        System.out.println("Ошибка. Пустой текст ");

                    }
                    try {
                        ComandParser.parser(inputCommand, console);

                    } catch (NoSuchMethodException e) {

                        System.out.println("Ошибка. " + e.getMessage());

                    }
                    break;
                }

                case "/exit" -> {

                    JsonManager.endWriterTasks(console);
                    count+=1;
                    break;

                }

                default -> {

                    System.out.println("Неправильная команда. Попытайтесь снова");

                }

            }

            break;
        }
    }
}
