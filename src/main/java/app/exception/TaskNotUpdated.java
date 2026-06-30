package app.exception;

import org.apache.commons.lang3.StringUtils;

public class TaskNotUpdated extends FundamentError{

    public TaskNotUpdated(String field, Throwable cause) {

        super(ListErrors.TASK_NOT_UPDATED, String.format("'%s' task field was not updated due to '%s'", StringUtils.capitalize(field), cause.getMessage()), cause);

    }

}
