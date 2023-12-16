package oncall.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import oncall.dto.request.OnCallRequest;

public class OnCallAssigner {
    private int days;
    private String startWeekday;

    private Deque<String> weekdayQueue;
    private Deque<String> holidayQueue;


    public OnCallAssigner(OnCallMonthWeekday onCallMonthWeekday, OnCallRequest onCallRequest) {
        initDays(onCallMonthWeekday);
        initQueues(onCallRequest);
    }

    private void initDays(OnCallMonthWeekday onCallMonthWeekday) {
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
}
