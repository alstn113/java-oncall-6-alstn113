package oncall;

import oncall.controller.OnCallController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OnCallController onCallController = createTemplateController();
        onCallController.run();
    }

    private static OnCallController createTemplateController() {
        return new OnCallController(
                new InputView(),
                new OutputView()
        );
    }
}
