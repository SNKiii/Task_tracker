package app.constants;


import java.nio.file.Path;

public class DataPathTextFile {


    public final static String PATH_FOR_IMAGE = "src/main/resources/images/file.png";
    public final static Path START_MENU = Path.of("src/main/resources/text/start.txt");
    public final  static Path BASE_COMAND = Path.of("src/main/resources/text/baseComand.txt");



    private DataPathTextFile() {
        throw new UnsupportedOperationException("Utility class");
    }


}
