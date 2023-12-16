package oncall.view;

import oncall.dto.response.AssignedOnCallResponse;

public interface OutputView {
    void printOnCallSchedule(AssignedOnCallResponse assignedOnCallResponse);
}
