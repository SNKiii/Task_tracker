package app.screen;

import app.constants.ConstantHandler;
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

        ImageToAscii.printFromFile(DataPathTextFile.PATH_FOR_IMAGE, 185);

        System.out.println(ConstantHandler.getStartMenu());

        String input = scanner.nextLine().trim();

        switch (input) {

            case "/start" -> {

                String inputCommand = "";

                System.out.println(ConstantHandler.getBaseCommand());

                JsonManager.startWriterTasks(console);

                do {

                    inputCommand = scanner.nextLine().trim();

                    if (inputCommand.isEmpty()) {

                        System.out.println("Ошибка. Пустой ввод команды");

                    }

                    try {

                        String returnCommand = ComandParser.parser(inputCommand, console);
                        System.out.println(returnCommand);

                    } catch (NoSuchMethodException e) {

                        System.out.println("Ошибка. " + e.getMessage());

                    }
                } while(!inputCommand.trim().equals("/exit"));

                JsonManager.endWriterTasks(console);
                System.exit(0);

            }
            case "/exit" -> {
                System.exit(0);
            }

            default -> {
                System.out.println("Неправильная команда. Попытайтесь снова");
            }
        }
    }
}