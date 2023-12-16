package oncall.controller;


import oncall.domain.OnCallAssigner;
import oncall.domain.OnCallMonthWeekday;
import oncall.dto.request.OnCallMonthWeekdayRequest;
import oncall.dto.request.OnCallRequest;
import oncall.dto.response.AssignedOnCallResponse;
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
        OnCallRequest onCallRequest = readWeekdayOnCall();
        OnCallAssigner onCallAssigner = new OnCallAssigner(onCallMonthWeekday, onCallRequest);
        outputView.printOnCallSchedule(AssignedOnCallResponse.from(onCallAssigner.getOnCallSchedules()));
    }

    private OnCallMonthWeekday readOnCallMonthWeekday() {
        return InputUtil.retryOnException(() -> {
            OnCallMonthWeekdayRequest dto = inputView.readOnCallMonthWeekday();
            return dto.toOnCallMonthWeekday();
        });
    }

    private OnCallRequest readWeekdayOnCall() {
        return InputUtil.retryOnException(() -> {
            OnCallRequest dto = new OnCallRequest();
            String weekdayOnCall = inputView.readWeekdayOnCall();
            dto.setWeekdayOnCall(weekdayOnCall);
            String holidayOnCall = inputView.readHolidayOnCall();
            dto.setHolidayOnCall(holidayOnCall);
            return dto;
        });
    }
}
