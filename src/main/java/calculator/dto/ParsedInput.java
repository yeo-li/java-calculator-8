package calculator.dto;

public class ParsedInput {
    private final String calculationBody;
    private final String customDelimiter;

    public ParsedInput(String calculationBody, String customDelimiter) {
        this.calculationBody = calculationBody;
        this.customDelimiter = customDelimiter;
    }

    public String getCalculationBody() {
        return calculationBody;
    }

    public String getCustomDelimiter() {
        return customDelimiter;
    }
}
