package calculator.constant.delimiter;

public enum DelimiterRegex {
    BASIC_REGEX(",|:");


    private final String constant;

    DelimiterRegex(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return this.constant;
    }
}
