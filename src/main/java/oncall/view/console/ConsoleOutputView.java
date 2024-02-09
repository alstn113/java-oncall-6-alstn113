package oncall.view.console;

import java.util.List;
import oncall.dto.response.MonthOnCallOrderResponse;
import oncall.dto.response.OnCallWorkerResponse;
import oncall.view.OutputView;

public class ConsoleOutputView implements OutputView {
    @Override
    public void printMonthOnCallOrder(MonthOnCallOrderResponse monthOnCallOrderResponse) {
        List<OnCallWorkerResponse> onCallWorkerResponses = monthOnCallOrderResponse.monthOnCallOrderResponse();

        System.out.println();
        for (OnCallWorkerResponse onCallWorkerResponse : onCallWorkerResponses) {
            int month = onCallWorkerResponse.month();
            int day = onCallWorkerResponse.day();
            String dayOfWeek = onCallWorkerResponse.dayOfWeek();
            if (onCallWorkerResponse.isWeekdayHoliday()) {
                dayOfWeek += "(휴일)";
            }
            String name = onCallWorkerResponse.name();

            System.out.printf("%d월 %d일 %s %s%n", month, day, dayOfWeek, name);
        }
    }
}
