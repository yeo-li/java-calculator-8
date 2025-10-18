package calculator.controller;

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
        String customDelimiter = calculatorService.parseCustomDelimiter(input);
        String calculationBody = calculatorService.extractCalculationBody(input);
        List<Integer> numbers = calculatorService.parseNumbers(calculationBody, customDelimiter);
        long result = calculatorService.sumAllNumbers(numbers);
        outputView.printOutput(result);
    }
}
