package oncall.config;

import oncall.controller.OnCallController;
import oncall.view.InputView;
import oncall.view.OutputView;
import oncall.view.console.ConsoleInputView;
import oncall.view.console.ConsoleOutputView;

public class AppConfig {
    private AppConfig() {
    }

    public static InputView consoleInputView() {
        return new ConsoleInputView();
    }

    public static OutputView consoleOutputView() {
        return new ConsoleOutputView();
    }

    public static OnCallController onCallController() {
        return new OnCallController(
                consoleInputView(),
                consoleOutputView()
        );
    }
}
