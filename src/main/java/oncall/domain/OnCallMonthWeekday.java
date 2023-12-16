package oncall.domain;

import oncall.constant.WeekdayConstant;
import oncall.exception.ErrorMessage;
import oncall.exception.InvalidInputException;

public record OnCallMonthWeekday(int month, String weekday) {
    public OnCallMonthWeekday {
        validateMonth(month);
        validateWeekday(weekday);
    }

    private void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new InvalidInputException(ErrorMessage.INVALID_MONTH);
        }
    }

    private void validateWeekday(String weekday) {
        if (!WeekdayConstant.WEEKDAYS.contains(weekday)) {
            throw new InvalidInputException(ErrorMessage.INVALID_WEEKDAY);
        }
    }
}
