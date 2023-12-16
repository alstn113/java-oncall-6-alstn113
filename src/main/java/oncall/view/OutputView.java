package oncall.view;

import java.util.List;
import oncall.domain.OnCallSchedule;
import oncall.dto.response.AssignedOnCallResponse;

public class OutputView {
    public void printOnCallSchedule(AssignedOnCallResponse assignedOnCallResponse) {
        List<OnCallSchedule> onCallSchedules = assignedOnCallResponse.onCallSchedules();
        for (OnCallSchedule onCallSchedule : onCallSchedules) {
            String weekday = onCallSchedule.weekday();
            if (onCallSchedule.isWeekdayHoliday()) {
                weekday += "(휴일)";
            }
            System.out.printf("%d월 %d일 %s %s%n", onCallSchedule.month(), onCallSchedule.day(), weekday,
                    onCallSchedule.name());
        }
    }
}
