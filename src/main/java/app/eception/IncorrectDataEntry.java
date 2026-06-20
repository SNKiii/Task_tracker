package app.eception;

public class IncorrectDataEntry  extends  RuntimeException {

    public IncorrectDataEntry () {

        super("Ошибка ввода");

    }

    public IncorrectDataEntry (String massege) {

        super(massege);

    }
}
