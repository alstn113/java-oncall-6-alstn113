package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.constant.MonthDays;
import oncall.constant.WeekdayConstant;
import oncall.dto.request.OnCallRequest;

public class OnCallAssigner {
    private int month;
    private int days;
    private String startWeekday;
    private final List<String> weekdayOnCall;
    private final List<String> holidayOnCall;
    private final List<OnCallSchedule> onCallSchedules = new ArrayList<>();

    public OnCallAssigner(OnCallMonthWeekday onCallMonthWeekday, OnCallRequest onCallRequest) {
        initMonthDays(onCallMonthWeekday);
        this.weekdayOnCall = new ArrayList<>(onCallRequest.getWeekdayOnCall());
        this.holidayOnCall = new ArrayList<>(onCallRequest.getHolidayOnCall());
        assignMembers();
    }

    public List<OnCallSchedule> getOnCallSchedules() {
        return onCallSchedules;
    }

    private void initMonthDays(OnCallMonthWeekday onCallMonthWeekday) {
        this.month = onCallMonthWeekday.month();
        this.days = MonthDays.DAYS.get(month - 1);
        this.startWeekday = onCallMonthWeekday.weekday();
    }

    private void assignMembers() {
        int startWeekdayIndex = WeekdayConstant.WEEKDAYS.indexOf(startWeekday);
        int weekdayIndex = 0;
        int holidayIndex = 0;

        for (int day = 1; day <= days; day++) {
            String weekday = WeekdayConstant.WEEKDAYS.get(startWeekdayIndex);

            boolean isWeekend = WeekdayConstant.isWeekend(weekday);
            boolean isLegalHoliday = LegalHoliday.isLegalHoliday(month, day);
            boolean isWeekdayHoliday = !isWeekend && isLegalHoliday;

            if (isWeekend || isLegalHoliday) {
                OnCallSchedule schedule = OnCallSchedule.of(month, day, weekday, isWeekdayHoliday,
                        holidayOnCall.get(holidayIndex));
                onCallSchedules.add(schedule);
                holidayIndex = (holidayIndex + 1) % holidayOnCall.size();
                startWeekdayIndex = (startWeekdayIndex + 1) % 7;
                continue;
            }

            OnCallSchedule schedule = OnCallSchedule.of(month, day, weekday, false, weekdayOnCall.get(weekdayIndex));
            onCallSchedules.add(schedule);
            weekdayIndex = (weekdayIndex + 1) % weekdayOnCall.size();

            startWeekdayIndex = (startWeekdayIndex + 1) % 7;
        }
    }
}