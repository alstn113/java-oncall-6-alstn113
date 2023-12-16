package oncall.constant;

import java.util.List;

public class WeekdayConstant {
    private WeekdayConstant() {
    }

    public static final List<String> WEEKDAYS = List.of("월", "화", "수", "목", "금", "토", "일");

    public static boolean isWeekend(String weekday) {
        return weekday.equals("토") || weekday.equals("일");
    }
}
