package oncall.exception;

public enum ErrorMessage {
    INPUT_NOT_A_NUMBER("입력값은 숫자여야 합니다."),
    INPUT_NOT_EMPTY("입력값은 비어있으면 안됩니다."),
    INVALID_MONTH("월은 1~12 사이의 숫자여야 합니다."),
    INVALID_WEEKDAY("월,화,수,목,금,토,일 중 하나여야 합니다."),
    INVALID_ONCALL_MONTH_WEEKDAY_FORMAT("월,요일 형식이 아닙니다."),
    MEMBER_DUPLICATE("비상 근무자는 중복될 수 없습니다."),
    INVALID_MEMBER_NAME_LENGTH("비상 근무자 이름은 5자 이하여야 합니다."),
    MEMBER_NOT_ASSIGNED_TWO("비상 근무자는 평일과 휴일에 모두 근무해야 합니다."),
    INVALID_MEMBER_SIZE("비상 근무자는 5명 이상 35명 이하여야 합니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
