package oncall.constant;

import java.util.List;

public class DaysOfWeekConstant {
    private DaysOfWeekConstant() {
    }

    public static List<String> DAYS_OF_WEEK = List.of("월", "화", "수", "목", "금", "토", "일");

    public static boolean isWeekend(String dayOfWeek) {
        return "토".equals(dayOfWeek) || "일".equals(dayOfWeek);
    }
}
