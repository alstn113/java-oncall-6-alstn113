package oncall.dto.request;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import oncall.exception.ErrorMessage;
import oncall.exception.InvalidInputException;
import oncall.view.util.InputUtil;

public class OnCallRequest {
    private List<String> weekdayOnCall = new ArrayList<>();
    private List<String> holidayOnCall = new ArrayList<>();

    private void validateDuplicate(List<String> members) {
        if (members.size() != members.stream().distinct().count()) {
            throw new InvalidInputException(ErrorMessage.MEMBER_DUPLICATE);
        }
    }

    private void validateNameLength(List<String> members) {
        if (members.stream().anyMatch(member -> member.length() > 5 || member.isBlank())) {
            throw new InvalidInputException(ErrorMessage.INVALID_MEMBER_NAME_LENGTH);
        }
    }

    private void validateNotAssignedTwo(List<String> members) {
        if (!new HashSet<>(members).equals(new HashSet<>(this.weekdayOnCall))) {
            throw new InvalidInputException(ErrorMessage.MEMBER_NOT_ASSIGNED_TWO);
        }
    }

    public List<String> getWeekdayOnCall() {
        return weekdayOnCall;
    }

    public void setWeekdayOnCall(String weekdayOnCallString) {
        List<String> members = InputUtil.parseToList(weekdayOnCallString, ",");
        validateDuplicate(members);
        validateNameLength(members);
        this.weekdayOnCall = members;
    }

    public List<String> getHolidayOnCall() {
        return holidayOnCall;
    }

    public void setHolidayOnCall(String holidayOnCallString) {
        List<String> members = InputUtil.parseToList(holidayOnCallString, ",");
        validateDuplicate(members);
        validateNameLength(members);
        validateNotAssignedTwo(members);
        this.holidayOnCall = members;
    }
}
