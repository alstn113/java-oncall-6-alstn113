package oncall.domain;

public record OnCallSchedule(int month, int day, String weekday, boolean isWeekdayHoliday, String name) {
    public static OnCallSchedule of(int month, int day, String weekday, boolean isLegalHoliday, String name) {
        return new OnCallSchedule(month, day, weekday, isLegalHoliday, name);
    }
}
