package oncall.exception;

public enum ErrorMessage {
    INPUT_NOT_A_NUMBER("입력값은 숫자여야 합니다."),
    INPUT_NOT_POSITIVE_NUMBER("입력값은 양수여야 합니다."),
    INPUT_NOT_EMPTY("입력값은 비어있으면 안됩니다."),
    INVALID_MONTH("월은 1~12 사이의 숫자여야 합니다."),
    INVALID_WEEKDAY("월,화,수,목,금,토,일 중 하나여야 합니다."),
    INVALID_ONCALL_MONTH_WEEKDAY_FORMAT("월,요일 형식이 아닙니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
