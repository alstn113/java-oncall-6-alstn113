package oncall.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import oncall.dto.request.OnCallRequest;

public class OnCallAssigner {
    private int month;
    private int days;
    private String startWeekday;
    private Deque<String> weekdayQueue;
    private Deque<String> holidayQueue;
    private final List<OnCallSchedule> onCallSchedules = new ArrayList<>();
    private static final List<String> weekdays = List.of("월", "화", "수", "목", "금", "토", "일");

    public OnCallAssigner(OnCallMonthWeekday onCallMonthWeekday, OnCallRequest onCallRequest) {
        initMonthDays(onCallMonthWeekday);
        initQueues(onCallRequest);
        assignMembers();
    }

    private void initMonthDays(OnCallMonthWeekday onCallMonthWeekday) {
        this.month = onCallMonthWeekday.month();
        this.days = 31;
        if (onCallMonthWeekday.month() == 2) {
            this.days = 28;
        }
        this.startWeekday = onCallMonthWeekday.weekday();
    }

    private void initQueues(OnCallRequest onCallRequest) {
        weekdayQueue = new ArrayDeque<>(onCallRequest.getWeekdayOnCall());
        holidayQueue = new ArrayDeque<>(onCallRequest.getHolidayOnCall());
    }

    public List<OnCallSchedule> getOnCallSchedules() {
        return onCallSchedules;
    }

    private void assignMembers() {
        int startWeekdayIndex = weekdays.indexOf(startWeekday);

        for (int i = 1; i <= days; i++) {
            if (startWeekdayIndex == 7) {
                startWeekdayIndex = 0;
            }

            String weekday = weekdays.get(startWeekdayIndex);
            boolean isWeekend = weekday.equals("토") || weekday.equals("일");
            boolean isLegalHoliday = LegalHoliday.isLegalHoliday(month, i);
            boolean isWeekdayHoliday = isWeekend && isLegalHoliday;

            if (isWeekend || isLegalHoliday) {
                String member = holidayQueue.poll();
                OnCallSchedule schedule = OnCallSchedule.of(month, i, weekday, isWeekdayHoliday, member);
                onCallSchedules.add(schedule);
                holidayQueue.add(member);
                startWeekdayIndex++;
                continue;
            }

            String member = weekdayQueue.poll();
            OnCallSchedule schedule = OnCallSchedule.of(month, i, weekday, false, member);
            onCallSchedules.add(schedule);
            weekdayQueue.add(member);

            startWeekdayIndex++;
        }
    }


}
