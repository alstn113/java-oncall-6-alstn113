package oncall.view;

import oncall.dto.request.OnCallOrderRequest;
import oncall.dto.request.OnCallStartDateRequest;

public interface InputView {

    OnCallStartDateRequest readOnCallStartDate();

    OnCallOrderRequest readWeekdayOnCallOrder();

    OnCallOrderRequest readHolidayOnCallOrder();
}
