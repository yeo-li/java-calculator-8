package calculator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorValidatorTest {
    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - \"//\"만 존재하는 경우")
    void validateCustomDelimiterOnlyCustomDelimiterPrefix() {
        // given
        String input = "//d1d2d3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> CalculatorValidator.validateCustomDelimiter(input)
        );
    }

    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - \\n만 존재하는 경우")
    void validateCustomDelimiterOnlyCustomDelimiterSuffix() {
        // given
        String input = "\\nd1d2d3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    CalculatorValidator.validateCustomDelimiter(input);
                }
        );
    }

    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - \"\\n\"이 \"//\" 보다 앞에 있는 경우")
    void validateCustomDelimiterCustomDelimiterInvalid() {
        // given
        String input = "\\nd//3d1d2d3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    CalculatorValidator.validateCustomDelimiter(input);
                }
        );
    }

    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - 커스텀 구분자 등록 문법이 맨 앞에 있지 않는 경우")
    void validateCustomDelimiterIsCustomDelimiterNotAtStart() {
        // given
        String input = "3d1d2d3//d\\n";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    CalculatorValidator.validateCustomDelimiter(input);
                }
        );
    }

    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - 커스텀 구분자가 숫자인 경우")
    void validateCustomDelimiterCustomDelimiterIsNumber() {
        // given
        String input = "//1\\n1d2d3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    CalculatorValidator.validateCustomDelimiter(input);
                }
        );
    }

    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - 커스텀 구분자에 \\n이 포함되어 있는 경우")
    void validateCustomDelimiterCustomDelimiterContainSuffix() {
        // given
        String input = "//\\na\\n\\n1\na\n2\na\n3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    CalculatorValidator.validateCustomDelimiter(input);
                }
        );
    }

    @Test
    @DisplayName("계산 부분에서 숫자 추출 - 숫자가 아닌 값이 있는 경우")
    void validateCalculationBodyNoNumber() {
        // given
        String[] input = new String[3];
        input[0] = "1";
        input[1] = "a";
        input[2] = "1";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    CalculatorValidator.validateCalculationBody(input);
                }
        );
    }

    @Test
    @DisplayName("계산 부분에서 숫자 추출 - Integer의 범위를 초과하는 경우")
    void validateCalculationBodyOverRange() {
        // given
        String[] input = new String[3];
        input[0] = "1";
        input[1] = "132412541235346356346";
        input[2] = "1";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    CalculatorValidator.validateCalculationBody(input);
                }
        );
    }
}