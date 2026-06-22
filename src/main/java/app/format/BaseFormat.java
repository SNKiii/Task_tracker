package app.format;

import app.model.Task;

import java.util.List;

public class BaseFormat {

    public static String formatCreateTask(Task task) {

        return "\n═══════════════════════════════════════════════\n"
                +
                task.getName()
                +
                "\n───────────────────────────────────────────────"
                +
                "\nСтадия выполнения: " + task.getStage()
                +
                "\nОценка важности: " + task.getImportanceLevel()
                +
                "\nЦели: "
                +
                task.getBody()
                +
                "\n───────────────────────────────────────────────"
                +
                "\nДата создания: " + task.getCreatedAt()
                +
                "\nСрок выполнения до - " + task.getDueDate()
                +
                "\n═══════════════════════════════════════════════\n";

    }

    public static String formatShowTask(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        int maxWidth = 40;

        String TOP = "╔" + "═".repeat(maxWidth - 2) + "╗";
        String DIVIDER = "╠" + "═".repeat(maxWidth - 2) + "╣";
        String BOTTOM = "╚" + "═".repeat(maxWidth - 2) + "╝";

        for (Task task : tasks) {
            sb.append(TOP).append("\n");
            sb.append("║  #").append(task.getId()).append(" ").append(task.getName()).append("\n");
            sb.append(DIVIDER).append("\n");
            sb.append("║  Цель:      ").append(task.getBody()).append("\n");
            sb.append("║  Важность:  ").append(task.getImportanceLevel()).append("\n");
            sb.append("║  Стадия:    ").append(task.getStage()).append("\n");
            sb.append("║  Срок:      ").append(task.getCreatedAt()).append(" → ").append(task.getDueDate()).append("\n");
            sb.append(BOTTOM).append("\n\n");
        }

        return sb.toString();
    }
}
