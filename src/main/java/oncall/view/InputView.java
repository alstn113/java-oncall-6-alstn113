package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.dto.request.OnCallMonthWeekdayRequest;

public class InputView {
    public OnCallMonthWeekdayRequest readOnCallMonthWeekday() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = Console.readLine();
        return new OnCallMonthWeekdayRequest(input);
    }
}
