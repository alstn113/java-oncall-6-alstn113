package oncall.domain;

public class OnCallWorker {
    private String name;
    private final int month;
    private final int day;
    private final String dayOfWeek;
    private final boolean isWeekdayHoliday;

    private OnCallWorker(String name, int month, int day, String dayOfWeek, boolean isWeekdayHoliday) {
        this.name = name;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.isWeekdayHoliday = isWeekdayHoliday;
    }

    public static OnCallWorker of(String name, int month, int day, String dayOfWeek, boolean isWeekdayHoliday) {
        return new OnCallWorker(name, month, day, dayOfWeek, isWeekdayHoliday);
    }

    public String getName() {
        return name;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isWeekdayHoliday() {
        return isWeekdayHoliday;
    }


    public void setName(String newName) {
        this.name = newName;
    }
}
