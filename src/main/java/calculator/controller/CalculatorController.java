package calculator.controller;

import calculator.dto.ParsedInput;
import calculator.sevice.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.List;

public class CalculatorController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CalculatorService calculatorService = new CalculatorService();

    public void run() {
        String input = inputView.readInput();
        ParsedInput parsedInput = parseInput(input);
        long result = calculate(parsedInput);
        outputView.printOutput(result);
    }

    public ParsedInput parseInput(String input) {
        String cleanedInput = calculatorService.removeAllSpaces(input);
        String customDelimiter = calculatorService.parseCustomDelimiter(cleanedInput);
        String calculationBody = calculatorService.extractCalculationBody(cleanedInput);

        return new ParsedInput(calculationBody, customDelimiter);
    }

    public long calculate(ParsedInput parsedInput) {
        List<Integer> numbers = calculatorService.parseNumbers(
                parsedInput.getCalculationBody(),
                parsedInput.getCustomDelimiter());
        return calculatorService.sumAllNumbers(numbers);
    }
}
