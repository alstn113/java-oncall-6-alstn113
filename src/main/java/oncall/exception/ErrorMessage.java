package oncall.exception;

public enum ErrorMessage {
    INPUT_NOT_A_NUMBER("입력값은 숫자여야 합니다."),
    INPUT_NOT_EMPTY("입력값은 비어있을 수 없습니다."),
    INVALID_ON_CALL_START_DATE_FORMAT("월, 요일 형식이 아닙니다."),
    INVALID_MONTH("유효하지 않은 월(month)입니다."),
    INVALID_DAY_OF_WEEK("유효하지 않은 요일(day of week)입니다."),
    INVALID_WORKERS_SIZE("유효하지 않은 근무자의 길이입니다."),
    INVALID_WORKER_NAME_LENGTH("유효하지 않은 근무자 이름의 길이입니다."),
    WORKER_DUPLICATED("중복된 근무자가 있습니다."),
    WORKER_NOT_ASSIGNED_FOR_WEEKDAY("평일에 배정되지 않은 근무자가 존재합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
