package oncall.dto.request;

import java.util.List;
import oncall.domain.OnCallMonthWeekday;
import oncall.exception.ErrorMessage;
import oncall.exception.InvalidInputException;
import oncall.view.util.InputUtil;

public record OnCallMonthWeekdayRequest(String input) {
    private static final String DELIMITER = ",";

    public OnCallMonthWeekday toOnCallMonthWeekday() {
        InputUtil.validateInputNotEmpty(input);

        List<String> monthAndWeekday = InputUtil.parseToList(input, DELIMITER);
        if (monthAndWeekday.size() != 2) {
            throw new InvalidInputException(ErrorMessage.INVALID_ONCALL_MONTH_WEEKDAY_FORMAT);
        }

        int month = InputUtil.parseToInt(monthAndWeekday.get(0));
        String weekday = monthAndWeekday.get(1);

        return new OnCallMonthWeekday(month, weekday);
    }
}
