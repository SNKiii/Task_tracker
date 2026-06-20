package app.constants;

import java.time.LocalDate;

public class DataDefaultNumber {

    public static final LocalDate LOCAL_DATE = LocalDate.now();
    public static final LocalDate FUTURE_LOCAL_DATE = LOCAL_DATE.plusYears(1);

    public static final Integer  DEFAULT_NUMBER_SIZE_TASKS = 7;
    public static final byte DEFAULT_IMPORTANCE_LEVEL_TASKS = 6;

    public static final String FUTURE_LOCAL_DATE_STRING = FUTURE_LOCAL_DATE.toString();




    private DataDefaultNumber() {
        throw new UnsupportedOperationException("Utility class");
    }
}
