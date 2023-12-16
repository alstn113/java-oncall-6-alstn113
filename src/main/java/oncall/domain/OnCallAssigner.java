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
        reassignMembers();
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

    private void reassignMembers() {
        for (int i = 1; i < onCallSchedules.size(); i++) {
            String previousMemberName = onCallSchedules.get(i - 1).name();
            OnCallSchedule currentSchedule = onCallSchedules.get(i);

            // 다를 경우 다음으로 넘어감
            if (!previousMemberName.equals(currentSchedule.name())) {
                continue;
            }

            boolean isWeekend = WeekdayConstant.isWeekend(currentSchedule.weekday());
            boolean isLegalHoliday = LegalHoliday.isLegalHoliday(currentSchedule.month(), currentSchedule.day());
            boolean isHolidayOrWeekend = isWeekend || isLegalHoliday;

            if (isHolidayOrWeekend) {
                int nextHolidayIndex = (holidayOnCall.indexOf(currentSchedule.name()) + 1) % holidayOnCall.size();
                String nextName = holidayOnCall.get(nextHolidayIndex);

                // onCallSchedules 중 i번째 인덱스 뒤의 값 중 다음 근무자와 같은 이름을 가진 근무자를 찾아서 이름을 스왑해줌
                String tmp = currentSchedule.name();
                currentSchedule.setName(nextName);
                for (int j = i + 1; j < onCallSchedules.size(); j++) {
                    OnCallSchedule nextSchedule = onCallSchedules.get(j);
                    if (nextSchedule.name().equals(nextName)) {
                        nextSchedule.setName(tmp);
                        break;
                    }
                }

                continue;
            }

            // 평일에 대해서 swap
            int nextWeekdayIndex = (weekdayOnCall.indexOf(currentSchedule.name()) + 1) % weekdayOnCall.size();
            String nextName = weekdayOnCall.get(nextWeekdayIndex);
            String tmp = currentSchedule.name();
            // onCallSchedules 중 i번째 인덱스 뒤의 값 중 다음 근무자와 같은 이름을 가진 근무자를 찾아서 이름을 스왑해줌
            currentSchedule.setName(nextName);
            for (int j = i + 1; j < onCallSchedules.size(); j++) {
                OnCallSchedule nextSchedule = onCallSchedules.get(j);
                if (nextSchedule.name().equals(nextName)) {
                    nextSchedule.setName(tmp);
                    break;
                }
            }
        }
    }
}