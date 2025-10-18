package calculator.sevice;

import java.util.ArrayList;
import java.util.List;

public class CalculatorService {

    public String parseCustomDelimiter(String input) {
        validateCustomDelimiter(input);

        if (!hasCustomDelimiter(input)) {
            return "";
        }

        int start = input.indexOf("//") + 2;
        int end = input.indexOf("\\n");

        return input.substring(start, end);
    }

    public String extractCalculationBody(String input) {
        validateCustomDelimiter(input);

        String numberSection = input;
        if (hasCustomDelimiter(input)) {
            int start = input.indexOf("\\n") + 3;
            numberSection = input.substring(start);
        }

        return numberSection;
    }

    public List<Integer> parseNumbers(String calculationBody, String customDelimiter) {
        String regex = ",|:|" + customDelimiter;

        String[] parsedCalculationBody = calculationBody.split(regex);
        validateCalculationBody(parsedCalculationBody);

        List<Integer> numbers = new ArrayList<>();

        // converter
        for (String number : parsedCalculationBody) {
            numbers.add(Integer.parseInt(number));
        }

        return numbers;
    }

    private void validateCalculationBody(String[] calculationBody) {
        for (String number : calculationBody) {
            if (!isNumber(number)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private boolean isNumber(String number) {
        for (char c : number.toCharArray()) {
            if ('0' > c || c > '9') {
                return false;
            }
        }
        return true;
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
