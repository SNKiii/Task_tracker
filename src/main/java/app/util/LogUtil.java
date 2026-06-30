package app.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class LogUtil {


    public static Logger getLogger (Class<?> clazz) {

        return LoggerFactory.getLogger(clazz);

    }

    private static final Logger USER_ACTION_LOGGER = LoggerFactory.getLogger("USER_ACTION");
    private static final Logger TASK_LOGGER = LoggerFactory.getLogger("TASK_LIFECYCLE");
    private static final Logger LIFECYCLE_LOGGER = LoggerFactory.getLogger("APP_LIFECYCLE");
    private static final Logger COMMAND_LOGGER = LoggerFactory.getLogger("COMMAND");

    public static void logUserAction(String action, String details) {

        USER_ACTION_LOGGER.info("\uD83D\uDC64 {} | {}", action, details);

    }

    public static void logUserAction(String action, Long taskId, String details) {

        if (taskId != null) {

            USER_ACTION_LOGGER.info("\uD83D\uDC64 {} | Task Id: {} | {}", action, taskId, details);

        } else {

            USER_ACTION_LOGGER.info("\uD83D\uDC64 {} | {}", action, details);

        }

    }

    public static void logUserAction(String action, Long taskId, String taskName, String details) {

        if (taskId != null && taskName != null) {

            USER_ACTION_LOGGER.info("\uD83D\uDC64 {} |Task Id: {} | Name: {} | {}", action, taskId, taskName, details);

        } else if (taskId != null) {

            USER_ACTION_LOGGER.info("\uD83D\uDC64 {} | Task Id: {} | {}", action, taskId, details);

        } else {

            USER_ACTION_LOGGER.info("\uD83D\uDC64 {} | {}", action, details);

        }

    }

    public static void logAppStart() {

        LIFECYCLE_LOGGER.info("Start Application");

    }

    public static void logAppStop() {

        LIFECYCLE_LOGGER.info("Application stopped");

    }

    public static void logCommand(String command) {

        COMMAND_LOGGER.debug("Command executed: {}", command);

    }


    public static void logDeleteTask(String name, Long id) {

        TASK_LOGGER.info("\uD83D\uDDD1\uFE0F Delete Task: Name: {} | Id({})", name, id);

    }

    public static void logUpdateTask(Long id, String field, Object oldTask, Object newTask) {

        TASK_LOGGER.info("✏\uFE0F Update Task (Id: {}) | Update field  {} | old Values: {} | new Values: {}", id, field, oldTask, newTask);

    }

    public  static void logCreatedTask(Long id, String importanceLevel) {

        TASK_LOGGER.info("\uD83D\uDCDD Created Task -> Id: {} | importance: {}", id, importanceLevel);

    }

    public static void logShowTask() {

        TASK_LOGGER.info("\uD83D\uDCD6  Show All Tasks");

    }

    public static void logShowTask(Long id) {

        TASK_LOGGER.info("\uD83D\uDCD6  Show Task -> Id: {}", id);

    }

    public static void logShowTask(List<String> firstAndLastNameTask, Integer size) {

        if (firstAndLastNameTask.size() == 2) {

            TASK_LOGGER.info("\uD83D\uDCD6 Show {} Tasks -> First Task: {} | Last Task: {}", size, firstAndLastNameTask.get(0), firstAndLastNameTask.get(1));

        } else {

            TASK_LOGGER.info("\uD83D\uDCD6 Show {} Tasks", size);

        }

    }

}
