package calculator.sevice;

import calculator.validator.CalculatorValidator;
import java.util.ArrayList;
import java.util.List;

public class CalculatorService {

    public String parseCustomDelimiter(String input) {
        CalculatorValidator.validateCustomDelimiter(input);

        if (!hasCustomDelimiter(input)) {
            return "";
        }

        int start = input.indexOf("//") + 2;
        int end = input.indexOf("\\n");

        return input.substring(start, end);
    }

    public String extractCalculationBody(String input) {
        CalculatorValidator.validateCustomDelimiter(input);

        String numberSection = input;
        if (hasCustomDelimiter(input)) {
            int start = input.indexOf("\\n") + 2;
            numberSection = input.substring(start);
        }

        return numberSection;
    }
    
    private boolean hasCustomDelimiter(String input) {
        return input.contains("//") && input.contains("\\n");
    }

    public List<Integer> parseNumbers(String calculationBody, String customDelimiter) {
        String regex = ",|:";
        if (!customDelimiter.isBlank()) {
            regex += "|" + customDelimiter;
        }

        String[] parsedCalculationBody = calculationBody.split(regex);
        CalculatorValidator.validateCalculationBody(parsedCalculationBody);

        List<Integer> numbers = new ArrayList<>();

        // converter
        for (String number : parsedCalculationBody) {
            numbers.add(Integer.parseInt(number));
        }

        return numbers;
    }

    public long sumAllNumbers(List<Integer> numbers) {
        long sum = 0;
        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }

}
