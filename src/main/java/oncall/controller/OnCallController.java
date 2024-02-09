package oncall.controller;

import oncall.domain.MonthOnCallOrder;
import oncall.domain.OnCallStartDate;
import oncall.domain.WeekOnCallOrder;
import oncall.dto.request.OnCallOrderRequest;
import oncall.dto.request.OnCallStartDateRequest;
import oncall.dto.response.MonthOnCallOrderResponse;
import oncall.view.InputView;
import oncall.view.OutputView;
import oncall.view.util.InputUtil;

public class OnCallController implements Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public OnCallController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public void run() {
        OnCallStartDate onCallStartDate = readOnCallStartDate();
        WeekOnCallOrder weekOnCallOrder = readWeekOnCallOrder();

        MonthOnCallOrder monthOnCallOrder = new MonthOnCallOrder(onCallStartDate, weekOnCallOrder);
        outputView.printMonthOnCallOrder(MonthOnCallOrderResponse.from(monthOnCallOrder));
    }

    private OnCallStartDate readOnCallStartDate() {
        return InputUtil.retryOnException(() -> {
            OnCallStartDateRequest dto = inputView.readOnCallStartDate();
            return dto.toOnCallStartDate();
        });
    }

    private WeekOnCallOrder readWeekOnCallOrder() {
        return InputUtil.retryOnException(() -> {
            WeekOnCallOrder weekOnCallOrder = new WeekOnCallOrder();

            OnCallOrderRequest weekdayOnCallOrderRequest = inputView.readWeekdayOnCallOrder();
            weekOnCallOrder.setWeekdayOnCallOrder(weekdayOnCallOrderRequest.toOnCallOrder());
            OnCallOrderRequest holidayOnCallOrderRequest = inputView.readHolidayOnCallOrder();
            weekOnCallOrder.setHolidayOnCallOrder(holidayOnCallOrderRequest.toOnCallOrder());

            return weekOnCallOrder;
        });
    }
}
