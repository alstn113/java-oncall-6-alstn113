package oncall.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import oncall.constant.MonthDays;
import oncall.constant.WeekdayConstant;
import oncall.dto.request.OnCallRequest;

public class OnCallAssigner {
    private int month;
    private int days;
    private String startWeekday;
    private Deque<String> weekdayQueue;
    private Deque<String> holidayQueue;
    private final List<OnCallSchedule> onCallSchedules = new ArrayList<>();

    public OnCallAssigner(OnCallMonthWeekday onCallMonthWeekday, OnCallRequest onCallRequest) {
        initMonthDays(onCallMonthWeekday);
        initQueues(onCallRequest);
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

    private void initQueues(OnCallRequest onCallRequest) {
        weekdayQueue = new ArrayDeque<>(onCallRequest.getWeekdayOnCall());
        holidayQueue = new ArrayDeque<>(onCallRequest.getHolidayOnCall());
    }

    private void assignMembers() {
        int startWeekdayIndex = WeekdayConstant.WEEKDAYS.indexOf(startWeekday);

        for (int i = 1; i <= days; i++) {
            if (startWeekdayIndex == 7) {
                startWeekdayIndex = 0;
            }

            String weekday = WeekdayConstant.WEEKDAYS.get(startWeekdayIndex);
            assignMemberForDay(weekday, i);
            startWeekdayIndex++;
        }
    }

    private void assignMemberForDay(String weekday, int day) {
        boolean isWeekend = WeekdayConstant.isWeekend(weekday);
        boolean isLegalHoliday = LegalHoliday.isLegalHoliday(month, day);
        boolean isWeekdayHoliday = !isWeekend && isLegalHoliday;

        if (isWeekend || isLegalHoliday) {
            assignMemberAndAddSchedule(holidayQueue, isWeekdayHoliday, weekday, day);
            return;
        }
        assignMemberAndAddSchedule(weekdayQueue, false, weekday, day);
    }

    private void assignMemberAndAddSchedule(Deque<String> queue, boolean isWeekdayHoliday, String weekday, int day) {
        String member = queue.poll();
        OnCallSchedule schedule = OnCallSchedule.of(month, day, weekday, isWeekdayHoliday, member);
        onCallSchedules.add(schedule);
        queue.add(member);
    }
}

//순번상 특정 근무자가 연속 2일 근무하게 되는 상황에는, 다음 근무자와 순서를 바꿔 편성한다.