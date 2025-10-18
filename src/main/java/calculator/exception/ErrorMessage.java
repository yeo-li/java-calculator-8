package calculator.exception;

public enum ErrorMessage {
    CUSTOM_DELIMITER_IS_INVALID("커스텀 지정자의 문법이 올바르지 않습니다."),
    CUSTOM_DELIMITER_CONTAIN_NUMBER("커스텀 지정자에 숫자가 포함되어 있습니다."),
    INVALID_NUMBER("숫자가 아닌 값이 포함되어 있습니다."),
    NUMBER_OUT_OF_RANGE("입력 가능한 숫자의 범위를 초과했습니다.");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
