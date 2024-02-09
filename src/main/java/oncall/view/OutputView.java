package oncall.view;

import oncall.dto.response.MonthOnCallOrderResponse;

public interface OutputView {
    void printMonthOnCallOrder(MonthOnCallOrderResponse monthOnCallOrderResponse);
}
