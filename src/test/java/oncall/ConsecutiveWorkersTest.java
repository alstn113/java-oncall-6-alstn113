package oncall;


import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("연속된 근무자들 순번 변경 테스트")
class ConsecutiveWorkersTest extends NsTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 연속_근무자_테스트() {
        assertSimpleTest(() -> {
            run(
                    "5,월",
                    "준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리",
                    "수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니"
            );
            assertThat(output()).contains(
                    "5월 1일 월 준팍" + LINE_SEPARATOR,
                    "5월 2일 화 도밥" + LINE_SEPARATOR,
                    "5월 3일 수 고니" + LINE_SEPARATOR,
                    "5월 4일 목 수아" + LINE_SEPARATOR,
                    "5월 5일 금(휴일) 루루" + LINE_SEPARATOR,
                    "5월 6일 토 수아" + LINE_SEPARATOR,
                    "5월 7일 일 글로" + LINE_SEPARATOR,
                    "5월 8일 월 루루" + LINE_SEPARATOR,
                    "5월 9일 화 글로" + LINE_SEPARATOR,
                    "5월 10일 수 솔로스타" + LINE_SEPARATOR,
                    "5월 11일 목 우코" + LINE_SEPARATOR,
                    "5월 12일 금 슬링키" + LINE_SEPARATOR,
                    "5월 13일 토 솔로스타" + LINE_SEPARATOR,
                    "5월 14일 일 우코" + LINE_SEPARATOR,
                    "5월 15일 월 참새" + LINE_SEPARATOR,
                    "5월 16일 화 도리" + LINE_SEPARATOR,
                    "5월 17일 수 준팍" + LINE_SEPARATOR,
                    "5월 18일 목 도밥" + LINE_SEPARATOR,
                    "5월 19일 금 고니" + LINE_SEPARATOR,
                    "5월 20일 토 슬링키" + LINE_SEPARATOR,
                    "5월 21일 일 참새" + LINE_SEPARATOR,
                    "5월 22일 월 수아" + LINE_SEPARATOR,
                    "5월 23일 화 루루" + LINE_SEPARATOR,
                    "5월 24일 수 글로" + LINE_SEPARATOR,
                    "5월 25일 목 솔로스타" + LINE_SEPARATOR,
                    "5월 26일 금 우코" + LINE_SEPARATOR,
                    "5월 27일 토 도리" + LINE_SEPARATOR,
                    "5월 28일 일 준팍" + LINE_SEPARATOR,
                    "5월 29일 월 슬링키" + LINE_SEPARATOR,
                    "5월 30일 화 참새" + LINE_SEPARATOR,
                    "5월 31일 수 도리"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

}
