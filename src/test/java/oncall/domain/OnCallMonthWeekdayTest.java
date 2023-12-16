package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.constant.WeekdayConstant;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OnCallMonthWeekdayTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void 올바른_월인지_확인한다(int month) {
        String weekday = WeekdayConstant.WEEKDAYS.get(0);
        OnCallMonthWeekday onCallMonthWeekday = new OnCallMonthWeekday(month, weekday);
        assertThat(onCallMonthWeekday.month()).isEqualTo(month);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 13})
    void 올바르지_않은_월인지_확인한다(int month) {
        String weekday = WeekdayConstant.WEEKDAYS.get(0);
        assertThatThrownBy(() -> new OnCallMonthWeekday(month, weekday))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"월", "화", "수", "목", "금", "토", "일"})
    void 올바른_요일인지_확인한다(String weekday) {
        int month = 1;
        OnCallMonthWeekday onCallMonthWeekday = new OnCallMonthWeekday(month, weekday);
        assertThat(onCallMonthWeekday.weekday()).isEqualTo(weekday);
    }

    @ParameterizedTest
    @ValueSource(strings = {"가", "나", "우웩"})
    void 올바르지_않은_요일인지_확인한다(String weekday) {
        int month = 1;
        assertThatThrownBy(() -> new OnCallMonthWeekday(month, weekday))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
