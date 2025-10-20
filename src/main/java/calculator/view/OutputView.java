package calculator.view;

import calculator.constant.message.ViewMessage;

public class OutputView {

    public void printOutput(long result) {
        System.out.println(ViewMessage.OUTPUT_MESSAGE.getMessage() + result);
    }
}
