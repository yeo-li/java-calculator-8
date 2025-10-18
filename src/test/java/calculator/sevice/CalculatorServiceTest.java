package calculator.sevice;

import java.util.ArrayList;
import java.util.List;
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
    void extractCalculationBodySuccess(String input, String expectedCalculationBody) {
        // when
        String calculationBody = calculatorService.extractCalculationBody(input);

        // then
        Assertions.assertEquals(expectedCalculationBody, calculationBody);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "1d2d3, d, 1,2,3",
                    "1park2park3, park, 1,2,3",
                    "1\\2\\3, \\, 1,2,3",
                    "1\\n2\\n3, \\n, 1,2,3",
                    "1:2:3, '', 1,2,3",
                    "1//2//3, //, 1,2,3",
                    "1|2|3, |, 1,2,3"
            }
    )
    @DisplayName("계산 부분으로부터 숫자 파싱 - 성공")
    void parseNumbersSuccess(String input, String customDelimiter, String expectedNumbers) {
        // given
        String[] inputNumbers = expectedNumbers.split("[,]");
        int[] expected = new int[inputNumbers.length];
        for (int i = 0; i < inputNumbers.length; i++) {
            expected[i] = Integer.parseInt(inputNumbers[i]);
        }

        // when
        List<Integer> numbers = calculatorService.parseNumbers(input, customDelimiter);

        // then
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], numbers.get(i));
        }
    }

    @Test
    @DisplayName("계산 부분에서 숫자 추출 - 숫자가 아닌 값이 있는 경우")
    void parseNumbersNoNumber() {
        // given
        String input = "1,a,3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    calculatorService.parseNumbers(input, "");
                }
        );
    }

    @Test
    @DisplayName("계산 부분에서 숫자 추출 - Integer의 범위를 초과하는 경우")
    void parseNumbersOverRange() {
        // given
        String input = "1,129413413412413432412,3";

        // when & then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    calculatorService.parseNumbers(input, "");
                }
        );
    }

    @Test
    @DisplayName("숫자 합 계산 - 성공")
    void sumAllNumbersSuccess() {
        // given
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3));
        long expected = 6;

        // when
        long actual = calculatorService.sumAllNumbers(input);

        // then
        Assertions.assertEquals(expected, actual);
    }
}