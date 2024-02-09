package oncall.domain;

import oncall.constant.DaysOfWeekConstant;
import oncall.constant.MonthDaysConstant;
import oncall.exception.ErrorMessage;
import oncall.exception.InvalidInputException;

public class OnCallStartDate {
    private static final int JAN = 1;
    private static final int DEC = 12;

    private final int month;
    private final String startDayOfWeek;

    public OnCallStartDate(int month, String startDayOfWeek) {
        validate(month, startDayOfWeek);
        this.month = month;
        this.startDayOfWeek = startDayOfWeek;
    }

    private void validate(int month, String startDayOfWeek) {
        validateMonth(month);
        validateDayOfWeek(startDayOfWeek);
    }

    private void validateMonth(int month) {
        if (month < JAN || month > DEC) {
            throw new InvalidInputException(ErrorMessage.INVALID_MONTH);
        }
    }

    private void validateDayOfWeek(String startDayOfWeek) {
        if (!DaysOfWeekConstant.DAYS_OF_WEEK.contains(startDayOfWeek)) {
            throw new InvalidInputException(ErrorMessage.INVALID_DAY_OF_WEEK);
        }
    }

    public int getMonth() {
        return month;
    }

    public int getDaysInMonth() {
        return MonthDaysConstant.DAYS.get(month - 1);
    }

    public String getStartDayOfWeek() {
        return startDayOfWeek;
    }
}
