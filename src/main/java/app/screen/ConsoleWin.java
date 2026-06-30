package app.screen;

import app.constants.ConstantHandler;
import app.constants.DataPathTextFile;
import app.parser.ComandParser;
import app.service.Console;
import app.util.LogUtil;
import org.slf4j.Logger;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleWin {

    private static final Console console = new Console();
    private static final Logger log = LogUtil.getLogger(ConsoleWin.class);

    public static void startWindow() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        log.info("Display turned on");

        ImageToAscii.printFromFile(DataPathTextFile.PATH_FOR_IMAGE, 185);

        System.out.println(ConstantHandler.getStartMenu());

        String input = scanner.nextLine().trim();
        log.debug("User input {}", input);

        switch (input) {

            case "/start" -> {

                log.info("Start work app");

                String inputCommand = "";

                System.out.println(ConstantHandler.getBaseCommand());

                console.getJsonManager().startWriterTasks(console);

                do {

                    inputCommand = scanner.nextLine().trim();

                    if (inputCommand.isEmpty()) {

                        System.out.println("Ошибка. Пустой ввод команды");
                        log.error("ERROR");

                    }

                    try {

                        String returnCommand = ComandParser.parser(inputCommand, console);
                        System.out.println(returnCommand);

                    } catch (NoSuchMethodException e) {

                        System.out.println("Ошибка. " + e.getMessage());

                    }
                } while(!inputCommand.trim().equals("/exit"));

                console.getJsonManager().endWriterTasks(console);
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