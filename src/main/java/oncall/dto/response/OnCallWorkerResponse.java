package oncall.dto.response;

import oncall.domain.OnCallWorker;

public record OnCallWorkerResponse(String name, int month, int day, String dayOfWeek, boolean isWeekdayHoliday) {
    public static OnCallWorkerResponse from(OnCallWorker onCallWorker) {
        return new OnCallWorkerResponse(
                onCallWorker.getName(),
                onCallWorker.getMonth(),
                onCallWorker.getDay(),
                onCallWorker.getDayOfWeek(),
                onCallWorker.isWeekdayHoliday()
        );
    }
}
