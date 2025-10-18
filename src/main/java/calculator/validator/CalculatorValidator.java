package calculator.validator;

import calculator.constant.DelimiterSymbol;
import calculator.exception.ErrorMessage;

public class CalculatorValidator {
    public static void validateCustomDelimiter(String input) {

        if (!input.contains(DelimiterSymbol.CUSTOM_PREFIX.getSymbol()) && !input.contains(
                DelimiterSymbol.CUSTOM_SUFFIX.getSymbol())) {
            return;
        }

        if (input.contains(DelimiterSymbol.CUSTOM_PREFIX.getSymbol()) && !input.contains(
                DelimiterSymbol.CUSTOM_SUFFIX.getSymbol())) {
            throw new IllegalArgumentException(ErrorMessage.CUSTOM_DELIMITER_IS_INVALID.getMessage());
        }

        if (input.contains(DelimiterSymbol.CUSTOM_SUFFIX.getSymbol()) && !input.contains(
                DelimiterSymbol.CUSTOM_PREFIX.getSymbol())) {
            throw new IllegalArgumentException(ErrorMessage.CUSTOM_DELIMITER_IS_INVALID.getMessage());
        }

        int prefixIndex = input.indexOf(DelimiterSymbol.CUSTOM_PREFIX.getSymbol());
        int suffixIndex = input.indexOf(DelimiterSymbol.CUSTOM_SUFFIX.getSymbol());

        if (prefixIndex != 0) {
            throw new IllegalArgumentException(ErrorMessage.CUSTOM_DELIMITER_IS_INVALID.getMessage());
        }

        if (prefixIndex > suffixIndex) {
            throw new IllegalArgumentException(ErrorMessage.CUSTOM_DELIMITER_IS_INVALID.getMessage());
        }

        int start = input.indexOf(DelimiterSymbol.CUSTOM_PREFIX.getSymbol()) + 2;
        int end = input.indexOf(DelimiterSymbol.CUSTOM_SUFFIX.getSymbol());
        String customDelimiter = input.substring(start, end);

        if (containNumber(customDelimiter)) {
            throw new IllegalArgumentException(ErrorMessage.CUSTOM_DELIMITER_CONTAIN_NUMBER.getMessage());
        }
    }

    public static void validateCalculationBody(String[] calculationBody) {
        for (String number : calculationBody) {
            if (!isNumber(number)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER.getMessage());
            }
        }
    }

    private static boolean isNumber(String number) {
        for (char c : number.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean containNumber(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
