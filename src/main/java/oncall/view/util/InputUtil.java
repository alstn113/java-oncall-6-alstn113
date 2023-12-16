package oncall.view.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import oncall.exception.ErrorMessage;
import oncall.exception.InvalidInputException;

public class InputUtil {
    private InputUtil() {
    }

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ErrorMessage.INPUT_NOT_A_NUMBER);
        }
    }

    public static String validateInputNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new InvalidInputException(ErrorMessage.INPUT_NOT_EMPTY);
        }
        return input;
    }


    public static List<String> parseToList(String input, String delimiter) {
        if (input == null || input.isBlank()) {
            return List.of();
        }
        String[] parts = input.split(delimiter, -1);
        return Arrays.stream(parts).map(String::trim).toList();
    }

    public static <T> T retryOnException(Supplier<T> supplier, boolean lineBreak) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                if (lineBreak) {
                    System.out.println();
                }
            }
        }
    }

    public static void retryOnException(Runnable runnable, boolean lineBreak) {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                if (lineBreak) {
                    System.out.println();
                }
            }
        }
    }

    public static <T> T retryOnException(Supplier<T> supplier) {
        return retryOnException(supplier, false);
    }

    public static void retryOnException(Runnable runnable) {
        retryOnException(runnable, false);
    }
}
