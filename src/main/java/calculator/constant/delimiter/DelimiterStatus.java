package calculator.constant.delimiter;

public enum DelimiterStatus {
    NONE("");

    private final String status;

    DelimiterStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
