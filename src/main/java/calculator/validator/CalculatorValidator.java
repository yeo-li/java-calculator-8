package calculator.validator;

public class CalculatorValidator {
    public static void validateCustomDelimiter(String input) {

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

        if (containNumber(customDelimiter)) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateCalculationBody(String[] calculationBody) {
        for (String number : calculationBody) {
            if (!isNumber(number)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static boolean isNumber(String number) {
        for (char c : number.toCharArray()) {
            if ('0' > c || c > '9') {
                return false;
            }
        }
        return true;
    }

    private static boolean containNumber(String input) {
        for (char c : input.toCharArray()) {
            if ('0' <= c && c <= '9') {
                return true;
            }
        }
        return false;
    }
}
