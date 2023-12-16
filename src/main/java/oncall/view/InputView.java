package oncall.view;

import oncall.dto.request.OnCallMonthWeekdayRequest;

public interface InputView {
    OnCallMonthWeekdayRequest readOnCallMonthWeekday();

    String readWeekdayOnCall();

    String readHolidayOnCall();
}
