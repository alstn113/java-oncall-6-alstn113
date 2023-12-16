package oncall.controller;


import oncall.domain.OnCallMonthWeekday;
import oncall.dto.request.OnCallMonthWeekdayRequest;
import oncall.view.InputView;
import oncall.view.OutputView;
import oncall.view.util.InputUtil;

public class OnCallController {
    private final InputView inputView;
    private final OutputView outputView;

    public OnCallController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        OnCallMonthWeekday onCallMonthWeekday = readOnCallMonthWeekday();
        System.out.println(onCallMonthWeekday);
    }

    private OnCallMonthWeekday readOnCallMonthWeekday() {
        return InputUtil.retryOnException(() -> {
            OnCallMonthWeekdayRequest dto = inputView.readOnCallMonthWeekday();
            return dto.toOnCallMonthWeekday();
        });
    }
}
