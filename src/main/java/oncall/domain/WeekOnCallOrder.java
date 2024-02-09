package oncall.domain;


import java.util.HashSet;
import java.util.Set;
import oncall.exception.ErrorMessage;
import oncall.exception.InvalidInputException;

public class WeekOnCallOrder {
    private OnCallOrder weekdayOnCallOrder;
    private OnCallOrder holidayOnCallOrder;

    public void setWeekdayOnCallOrder(OnCallOrder weekdayOnCallOrder) {
        this.weekdayOnCallOrder = weekdayOnCallOrder;
    }

    public void setHolidayOnCallOrder(OnCallOrder holidayOnCallOrder) {
        validateNotAssignedForWeekday(holidayOnCallOrder);
        this.holidayOnCallOrder = holidayOnCallOrder;
    }

    private void validateNotAssignedForWeekday(OnCallOrder holidayOnCallOrder) {
        Set<String> weekdayOnCallWorkers = new HashSet<>(weekdayOnCallOrder.getWorkers());
        Set<String> holidayOnCallWorkers = new HashSet<>(holidayOnCallOrder.getWorkers());

        if (!weekdayOnCallWorkers.equals(holidayOnCallWorkers)) {
            throw new InvalidInputException(ErrorMessage.WORKER_NOT_ASSIGNED_FOR_WEEKDAY);
        }
    }

    public OnCallOrder getWeekdayOnCallOrder() {
        return weekdayOnCallOrder;
    }

    public OnCallOrder getHolidayOnCallOrder() {
        return holidayOnCallOrder;
    }
}
