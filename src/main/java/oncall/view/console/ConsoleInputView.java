package oncall.view.console;

import camp.nextstep.edu.missionutils.Console;
import oncall.dto.request.OnCallOrderRequest;
import oncall.dto.request.OnCallStartDateRequest;
import oncall.view.InputView;

public class ConsoleInputView implements InputView {
    @Override
    public OnCallStartDateRequest readOnCallStartDate() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = Console.readLine();
        return new OnCallStartDateRequest(input);
    }

    @Override
    public OnCallOrderRequest readWeekdayOnCallOrder() {
        System.out.println("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String input = Console.readLine();
        return new OnCallOrderRequest(input);
    }

    @Override
    public OnCallOrderRequest readHolidayOnCallOrder() {
        System.out.println("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String input = Console.readLine();
        return new OnCallOrderRequest(input);
    }
}
