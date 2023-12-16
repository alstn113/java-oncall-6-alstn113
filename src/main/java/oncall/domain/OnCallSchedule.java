package oncall.domain;

public class OnCallSchedule {
    private final int month;
    private final int day;
    private final String weekday;
    private final boolean isWeekdayHoliday;
    private String name;

    private OnCallSchedule(int month, int day, String weekday, boolean isWeekdayHoliday, String name) {
        this.month = month;
        this.day = day;
        this.weekday = weekday;
        this.isWeekdayHoliday = isWeekdayHoliday;
        this.name = name;
    }

    public static OnCallSchedule of(int month, int day, String weekday, boolean isLegalHoliday, String name) {
        return new OnCallSchedule(month, day, weekday, isLegalHoliday, name);
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public String weekday() {
        return weekday;
    }

    public boolean isWeekdayHoliday() {
        return isWeekdayHoliday;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
