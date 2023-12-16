package oncall.config;

import oncall.controller.OnCallController;
import oncall.view.console.ConsoleInputView;
import oncall.view.console.ConsoleOutputView;

public class AppConfig {
    private AppConfig() {
    }

    public static OnCallController onCallController() {
        return new OnCallController(
                new ConsoleInputView(),
                new ConsoleOutputView()
        );
    }
}
