package calculator.sevice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @ParameterizedTest
    @CsvSource(
            {
                    "//d\\n1d2d3, d",
                    "//park\\n1park2park3, park",
                    "//\\n\\n1\\2\\3, \\n",
                    "//\\n1\\2\\3, ''",
                    "1:2:3, ''",
                    "////\\n1//2//3, //"
            }
    )
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - 성공")
    void parseCustomDelimiterSuccess(String input, String expectedDelimiter) {
        // when
        String customDelimiter = calculatorService.parseCustomDelimiter(input);

        // then
        Assertions.assertEquals(expectedDelimiter, customDelimiter);
    }

    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - \"//\"만 존재하는 경우")
    void parseCustomDelimiterCustomDelimiterInvalid() {
        // given
        String input = "//d1d2d3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    calculatorService.parseCustomDelimiter(input);
                }
        );
    }

    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - \\n만 존재하는 경우")
    void parseCustomDelimiterCustomDelimiterInvalid2() {
        // given
        String input = "\\nd1d2d3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    calculatorService.parseCustomDelimiter(input);
                }
        );
    }

    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - \"\\n\"이 \"//\" 보다 앞에 있는 경우")
    void parseCustomDelimiterCustomDelimiterInvalid3() {
        // given
        String input = "\\nd//3d1d2d3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    calculatorService.parseCustomDelimiter(input);
                }
        );
    }

    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - 커스텀 구분자 등록 문법이 맨 앞에 있지 않는 경우")
    void parseCustomDelimiterCustomDelimiterInvalid4() {
        // given
        String input = "3d1d2d3//d\\n";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    calculatorService.parseCustomDelimiter(input);
                }
        );
    }

    @Test
    @DisplayName("사용자 입력값으로부터 커스텀 구분자 추출 - 커스텀 구분자가 숫자인 경우")
    void parseCustomDelimiterCustomDelimiterIsNumber() {
        // given
        String input = "//1\\n1d2d3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    calculatorService.parseCustomDelimiter(input);
                }
        );
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "//d\\n1d2d3, 1d2d3",
                    "//park\\n1park2park3, 1park2park3",
                    "//\\n\\n1\\2\\3, 1\\2\\3",
                    "//\\n1\\2\\3, 1\\2\\3",
                    "1:2:3, 1:2:3",
                    "////\\n1//2//3, 1//2//3"
            }
    )
    @DisplayName("사용자 입력값으로부터 계산 부분 추출 - 성공")
    void extractCalculationBodySuccess(String input, String expectedDelimiter) {
        // when
        String customDelimiter = calculatorService.extractCalculationBody(input);

        // then
        Assertions.assertEquals(expectedDelimiter, customDelimiter);
    }


}