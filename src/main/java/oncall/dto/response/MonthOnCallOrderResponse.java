package oncall.dto.response;

import java.util.List;
import oncall.domain.MonthOnCallOrder;

public record MonthOnCallOrderResponse(List<OnCallWorkerResponse> monthOnCallOrderResponse) {
    public static MonthOnCallOrderResponse from(MonthOnCallOrder monthOnCallOrder) {
        List<OnCallWorkerResponse> onCallWorkerResponses = monthOnCallOrder.getOnCallSchedule()
                .stream()
                .map(OnCallWorkerResponse::from)
                .toList();

        return new MonthOnCallOrderResponse(onCallWorkerResponses);
    }
}
