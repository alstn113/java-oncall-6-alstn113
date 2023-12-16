package oncall.domain;

import oncall.constant.WeekdayConstant;
import oncall.exception.ErrorMessage;
import oncall.exception.InvalidInputException;

public record OnCallMonthWeekday(int month, String weekday) {
    private static final int JANUARY = 1;
    private static final int DECEMBER = 12;

    public OnCallMonthWeekday {
        validateMonth(month);
        validateWeekday(weekday);
    }

    private void validateMonth(int month) {
        if (month < JANUARY || month > DECEMBER) {
            throw new InvalidInputException(ErrorMessage.INVALID_MONTH);
        }
    }

    private void validateWeekday(String weekday) {
        if (!WeekdayConstant.WEEKDAYS.contains(weekday)) {
            throw new InvalidInputException(ErrorMessage.INVALID_WEEKDAY);
        }
    }
}
