package oncall.view.console;

import camp.nextstep.edu.missionutils.Console;
import oncall.dto.request.OnCallMonthWeekdayRequest;
import oncall.view.InputView;

public class ConsoleInputView implements InputView {
    public OnCallMonthWeekdayRequest readOnCallMonthWeekday() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = Console.readLine();
        return new OnCallMonthWeekdayRequest(input);
    }

    public String readWeekdayOnCall() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return Console.readLine();
    }

    public String readHolidayOnCall() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return Console.readLine();
    }
}
