package oncall.exception;

public enum ErrorMessage {
    INPUT_NOT_A_NUMBER("입력값은 숫자여야 합니다."),
    INPUT_NOT_POSITIVE_NUMBER("입력값은 양수여야 합니다."),
    INPUT_NOT_EMPTY("입력값은 비어있으면 안됩니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
