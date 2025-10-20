package calculator.view;

import calculator.constant.message.ViewMessage;
import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readInput() {
        System.out.println(ViewMessage.INPUT_MESSAGE.getMessage());
        return Console.readLine();
    }
}
