package app;

import app.screen.ConsoleWin;
import app.util.LogUtil;
import org.slf4j.Logger;

public class Main {

    private static final Logger log = LogUtil.getLogger(Main.class);

    public static void main(String[] args) {

        LogUtil.logAppStart();
        log.info("Task Tracker started");
        try {

            ConsoleWin.startWindow();

        } catch (Exception e) {

            log.error("Critical error in application {}", String.valueOf(e));

        } finally {

            LogUtil.logAppStop();
            log.info("Task Tracker stopped");

        }

    }
}