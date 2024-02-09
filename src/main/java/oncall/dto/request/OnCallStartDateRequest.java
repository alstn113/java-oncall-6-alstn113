package oncall.dto.request;

import oncall.domain.OnCallStartDate;
import oncall.exception.ErrorMessage;
import oncall.exception.InvalidInputException;
import oncall.view.util.InputUtil;

public record OnCallStartDateRequest(String input) {
    private static final int SPLIT_LENGTH = 2;

    public OnCallStartDate toOnCallStartDate() {
        InputUtil.validateInputNotEmpty(input);

        String[] monthAndDayOfWeek = InputUtil.splitByComma(input);
        if (monthAndDayOfWeek.length != SPLIT_LENGTH) {
            throw new InvalidInputException(ErrorMessage.INVALID_ON_CALL_START_DATE_FORMAT);
        }

        int month = InputUtil.parseToInt(monthAndDayOfWeek[0]);
        String dayOfWeek = monthAndDayOfWeek[1];

        return new OnCallStartDate(month, dayOfWeek);
    }
}
