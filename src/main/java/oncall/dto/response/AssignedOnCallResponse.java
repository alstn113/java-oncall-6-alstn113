package oncall.dto.response;

import java.util.List;
import oncall.domain.OnCallSchedule;

public record AssignedOnCallResponse(List<OnCallSchedule> onCallSchedules) {
    public static AssignedOnCallResponse from(List<OnCallSchedule> onCallSchedules) {
        return new AssignedOnCallResponse(onCallSchedules);
    }
}
