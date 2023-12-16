package oncall.domain;

import java.util.List;
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
        List<String> weekdays = List.of("월", "화", "수", "목", "금", "토", "일");
        if (!weekdays.contains(weekday)) {
            throw new InvalidInputException(ErrorMessage.INVALID_WEEKDAY);
        }
    }
}
