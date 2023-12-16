package oncall.dto.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class OnCallRequestTest {

    @Test
    void 정상적으로_평일_근무자가_등록된다() {
        OnCallRequest onCallRequest = new OnCallRequest();
        onCallRequest.setWeekdayOnCall("John,Mary,Bob,Alice,Chaie");
        assertThat(onCallRequest.getWeekdayOnCall()).containsExactly("John", "Mary", "Bob", "Alice", "Chaie");
    }

    @Test
    void 두_번_이상_등록된_근무자가_있으면_예외가_발생한다() {
        OnCallRequest onCallRequest = new OnCallRequest();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> onCallRequest.setWeekdayOnCall("John,Mary,Bob,John,Alice"));
    }

    @Test
    void 근무자의_이름은_5자_이하여야한다() {
        OnCallRequest onCallRequest = new OnCallRequest();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> onCallRequest.setWeekdayOnCall("John,Mary,Bob,Alice,CharlieLongName"));
    }

    @Test
    void 총_근무자_수는_5명보다_작지_않다() {
        OnCallRequest onCallRequest = new OnCallRequest();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> onCallRequest.setWeekdayOnCall("John,Mary,Bob,Alice"));
    }

    @Test
    void 총_근무자_수는_35명보다_많지_않다() {
        OnCallRequest onCallRequest = new OnCallRequest();
        String members = "John,Mary,Bob,Alice,Charlie,David,Emma,Frank,George,Henry,Ian,John,Mary,Bob,Alice,Charlie,David,Emma,Frank,George,Henry,Ian,John,Mary,Bob,Alice,Charlie,David,Emma,Frank,George,Henry,Ian,John,Mary,Bob,Alice,Charlie,David,Emma,Frank,George,Henry,Ian";
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> onCallRequest.setWeekdayOnCall(members));

    }

    @Test
    void 평일과_휴일의_근무자가_같지_않으면_안된다() {
        String weekdayOnCall = "John,Mary,Bob,Alice,Chaie";
        String holidayOnCall = "John,Mary,Bob,Ake,Chaie";
        OnCallRequest onCallRequest = new OnCallRequest();
        onCallRequest.setWeekdayOnCall(weekdayOnCall);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> onCallRequest.setHolidayOnCall(holidayOnCall));
    }
}
