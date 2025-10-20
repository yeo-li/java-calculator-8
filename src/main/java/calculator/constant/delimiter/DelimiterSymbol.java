package calculator.constant.delimiter;

public enum DelimiterSymbol {
    CUSTOM_PREFIX("//"),
    CUSTOM_SUFFIX("\\n");

    private final String symbol;

    DelimiterSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
