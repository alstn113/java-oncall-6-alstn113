package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LegalHolidayTest {
    @ParameterizedTest
    @CsvSource(value = {"1,1", "3,1", "5,5", "6,6", "8,15", "10,3", "10,9", "12,25"})
    void 법정_공휴일인_경우(int month, int day) {
        assertThat(LegalHoliday.isLegalHoliday(month, day)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,2", "3,2", "5,6", "6,7", "8,16", "10,4", "10,10", "12,26"})
    void 법정_공휴일이_아닌지_경우(int month, int day) {
        assertThat(LegalHoliday.isLegalHoliday(month, day)).isFalse();
    }
}