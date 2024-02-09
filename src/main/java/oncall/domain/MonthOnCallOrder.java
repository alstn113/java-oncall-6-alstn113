package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.constant.DaysOfWeekConstant;

public class MonthOnCallOrder {
    private final List<String> weekdayOnCallOrder;
    private final List<String> holidayOnCallOrder;
    private final List<OnCallWorker> onCallSchedule = new ArrayList<>();

    public MonthOnCallOrder(OnCallStartDate onCallStartDate, WeekOnCallOrder weekOnCallOrder) {
        this.weekdayOnCallOrder = weekOnCallOrder.getWeekdayOnCallOrder().getWorkers();
        this.holidayOnCallOrder = weekOnCallOrder.getHolidayOnCallOrder().getWorkers();
        assignOnCallWorkers(onCallStartDate);
        reassignOnCallWorkers();
    }

    private void assignOnCallWorkers(OnCallStartDate onCallStartDate) {
        int month = onCallStartDate.getMonth();
        int daysInMonth = onCallStartDate.getDaysInMonth();
        String startDayOfWeek = onCallStartDate.getStartDayOfWeek();

        int dayOfWeekIndex = DaysOfWeekConstant.DAYS_OF_WEEK.indexOf(startDayOfWeek);
        int weekdayIndex = 0;
        int holidayIndex = 0;

        for (int day = 1; day <= daysInMonth; day++) {
            String dayOfWeek = DaysOfWeekConstant.DAYS_OF_WEEK.get(dayOfWeekIndex);

            boolean isWeekend = DaysOfWeekConstant.isWeekend(dayOfWeek);
            boolean isLegalHoliday = LegalHoliday.isLegalHoliday(month, day);
            boolean isWeekdayHoliday = !isWeekend && isLegalHoliday;

            if (isWeekend || isLegalHoliday) {
                holidayIndex = getDayIndex(holidayIndex, holidayOnCallOrder, month, day, dayOfWeek, isWeekdayHoliday);
            } else {
                weekdayIndex = getDayIndex(weekdayIndex, weekdayOnCallOrder, month, day, dayOfWeek, false);
            }

            dayOfWeekIndex = (dayOfWeekIndex + 1) % 7;
        }
    }

    private int getDayIndex(int dayIndex, List<String> dayOnCallOrder, int month, int day, String dayOfWeek,
                            boolean isWeekdayHoliday) {
        String workerName = dayOnCallOrder.get(dayIndex);
        OnCallWorker onCallWorker = OnCallWorker.of(workerName, month, day, dayOfWeek, isWeekdayHoliday);
        onCallSchedule.add(onCallWorker);
        dayIndex = (dayIndex + 1) % dayOnCallOrder.size();
        return dayIndex;
    }

    private void reassignOnCallWorkers() {
        for (int day = 1; day < onCallSchedule.size(); day++) {
            String previousWorkerName = onCallSchedule.get(day - 1).getName();
            OnCallWorker currentWorker = onCallSchedule.get(day);

            if (!previousWorkerName.equals(currentWorker.getName())) {
                continue;
            }

            boolean isWeekend = DaysOfWeekConstant.isWeekend(currentWorker.getDayOfWeek());
            boolean isLegalHoliday = LegalHoliday.isLegalHoliday(currentWorker.getMonth(), currentWorker.getDay());
            boolean isHoliday = isWeekend || isLegalHoliday;

            if (isHoliday) {
                reassignOnCallWorkers(weekdayOnCallOrder, currentWorker, day);
            } else {
                reassignOnCallWorkers(holidayOnCallOrder, currentWorker, day);
            }
        }
    }

    private void reassignOnCallWorkers(List<String> onCallOrder, OnCallWorker currentWorker, int day) {
        int nextDayIndex = (onCallOrder.indexOf(currentWorker.getName()) + 1) % onCallOrder.size();
        String nextWorkerName = onCallOrder.get(nextDayIndex);

        swapWorkerOrder(currentWorker, nextWorkerName, day);
    }

    private void swapWorkerOrder(OnCallWorker currentWorker, String nextWorkerName, int day) {
        String currentWorkerName = currentWorker.getName();
        currentWorker.setName(nextWorkerName);

        for (int i = day + 1; i < onCallSchedule.size(); i++) {
            OnCallWorker nextOnCallWorker = onCallSchedule.get(i);
            if (nextOnCallWorker.getName().equals(nextWorkerName)) {
                nextOnCallWorker.setName(currentWorkerName);
                return;
            }
        }
    }

    public List<OnCallWorker> getOnCallSchedule() {
        return onCallSchedule;
    }
}
