package oncall.dto.request;

import java.util.Arrays;
import java.util.List;
import oncall.domain.OnCallOrder;
import oncall.view.util.InputUtil;

public record OnCallOrderRequest(String input) {
    public OnCallOrder toOnCallOrder() {
        String[] workersArray = InputUtil.splitByComma(input);
        List<String> workersList = Arrays.stream(workersArray).toList();
        return new OnCallOrder(workersList);
    }
}
