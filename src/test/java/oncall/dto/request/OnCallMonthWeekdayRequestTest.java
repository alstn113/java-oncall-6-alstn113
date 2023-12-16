package oncall.dto.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.domain.OnCallMonthWeekday;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OnCallMonthWeekdayRequestTest {
    @ParameterizedTest
    @ValueSource(strings = {"1,월", "2,화", "3,수", "4,목", "5,금", "6,토", "7,일"})
    void 올바른_입력인지_확인한다(String input) {
        OnCallMonthWeekdayRequest onCallMonthWeekdayRequest = new OnCallMonthWeekdayRequest(input);
        OnCallMonthWeekday onCallMonthWeekday = onCallMonthWeekdayRequest.toOnCallMonthWeekday();
        assertThat(onCallMonthWeekday.month()).isBetween(1, 12);
        assertThat(onCallMonthWeekday.weekday()).isIn("월", "화", "수", "목", "금", "토", "일");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,월,화", "2,화,수", "3,수,목", "4,목,금", "5,금,토", "6,토,일", "7,일,월"})
    void 올바르지_않은_입력인지_확인한다(String input) {
        assertThatThrownBy(() -> new OnCallMonthWeekdayRequest(input).toOnCallMonthWeekday())
                .isInstanceOf(IllegalArgumentException.class);
    }
}