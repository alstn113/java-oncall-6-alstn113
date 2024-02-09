package oncall.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import oncall.exception.ErrorMessage;
import oncall.exception.InvalidInputException;

public class OnCallOrder {
    private static final int WORKER_NAME_MAX_LENGTH = 5;
    private static final int WORKERS_MIN_SIZE = 5;
    private static final int WORKERS_MAX_SIZE = 35;

    private final List<String> workers;

    public OnCallOrder(List<String> workers) {
        validate(workers);
        this.workers = new ArrayList<>(workers);
    }

    private void validate(List<String> workers) {
        validateWorkersSize(workers);
        validateWorkerNameLength(workers);
        validateDuplicate(workers);
    }

    private void validateWorkersSize(List<String> workers) {
        if (workers.size() < WORKERS_MIN_SIZE || workers.size() > WORKERS_MAX_SIZE) {
            throw new InvalidInputException(ErrorMessage.INVALID_WORKERS_SIZE);
        }
    }

    private void validateWorkerNameLength(List<String> workers) {
        if (workers.stream().anyMatch(worker -> worker.isBlank() || worker.length() > WORKER_NAME_MAX_LENGTH)) {
            throw new InvalidInputException(ErrorMessage.INVALID_WORKER_NAME_LENGTH);
        }
    }

    private void validateDuplicate(List<String> workers) {
        if (workers.size() != workers.stream().distinct().count()) {
            throw new InvalidInputException(ErrorMessage.WORKER_DUPLICATED);
        }
    }

    public List<String> getWorkers() {
        return Collections.unmodifiableList(workers);
    }
}
