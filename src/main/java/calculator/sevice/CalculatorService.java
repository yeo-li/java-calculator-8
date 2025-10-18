package calculator.sevice;

import calculator.constant.DelimiterRegex;
import calculator.constant.DelimiterStatus;
import calculator.constant.DelimiterSymbol;
import calculator.validator.CalculatorValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CalculatorService {

    public String parseCustomDelimiter(String input) {
        CalculatorValidator.validateCustomDelimiter(input);

        if (!hasCustomDelimiter(input)) {
            return DelimiterStatus.NONE.getStatus();
        }

        int start = input.indexOf(DelimiterSymbol.CUSTOM_PREFIX.getSymbol()) + 2;
        int end = input.lastIndexOf(DelimiterSymbol.CUSTOM_SUFFIX.getSymbol());

        return input.substring(start, end);
    }

    public String extractCalculationBody(String input) {
        CalculatorValidator.validateCustomDelimiter(input);

        String numberSection = input;
        if (hasCustomDelimiter(input)) {
            int start = input.lastIndexOf(DelimiterSymbol.CUSTOM_SUFFIX.getSymbol()) + 2;
            numberSection = input.substring(start);
        }

        return numberSection;
    }

    private boolean hasCustomDelimiter(String input) {
        return input.contains(DelimiterSymbol.CUSTOM_PREFIX.getSymbol()) && input.contains(
                DelimiterSymbol.CUSTOM_SUFFIX.getSymbol());
    }

    public List<Integer> parseNumbers(String calculationBody, String customDelimiter) {
        String[] parsedCalculationBody = calculationBody.split(createRegex(customDelimiter));
        CalculatorValidator.validateCalculationBody(parsedCalculationBody);

        return convertNumberList(parsedCalculationBody);
    }

    private String createRegex(String customDelimiter) {
        String regex = DelimiterRegex.BASIC_REGEX.getConstant();
        if (!customDelimiter.isBlank()) {
            regex += "|" + Pattern.quote(customDelimiter);
        }

        return regex;
    }

    private List<Integer> convertNumberList(String[] calculationBody) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : calculationBody) {
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
