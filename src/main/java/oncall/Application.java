package oncall;

import oncall.config.AppConfig;
import oncall.controller.OnCallController;

public class Application {
    public static void main(String[] args) {
        OnCallController onCallController = AppConfig.onCallController();
        onCallController.run();
    }
}
