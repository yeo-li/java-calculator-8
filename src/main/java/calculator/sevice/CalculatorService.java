package calculator.sevice;

public class CalculatorService {

    public String extractCustomDelimiter(String input) {
        validateCustomDelimiter(input);

        if (!hasCustomDelimiter(input)) {
            return ""; // todo: constant
        }

        int start = input.indexOf("//") + 2;
        int end = input.indexOf("\\n");

        return input.substring(start, end);
    }

    private boolean hasCustomDelimiter(String input) {
        return input.contains("//") && input.contains("\\n");
    }

    private void validateCustomDelimiter(String input) {

        if (!input.contains("//") && !input.contains("\\n")) {
            return;
        }

        if (input.contains("//") && !input.contains("\\n")) {
            throw new IllegalArgumentException();
        }

        if (input.contains("\\n") && !input.contains("//")) {
            throw new IllegalArgumentException();
        }

        int prefixIndex = input.indexOf("//");
        int suffixIndex = input.indexOf("\\n");

        if (prefixIndex != 0) {
            throw new IllegalArgumentException();
        }

        if (prefixIndex > suffixIndex) {
            throw new IllegalArgumentException();
        }

        int start = input.indexOf("//") + 2;
        int end = input.indexOf("\\n");
        String customDelimiter = input.substring(start, end);

        for (char c : customDelimiter.toCharArray()) {
            if ('0' <= c && c <= '9') {
                throw new IllegalArgumentException();
            }
        }
    }

}
