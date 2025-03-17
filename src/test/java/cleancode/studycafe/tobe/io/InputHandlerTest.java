package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputHandlerTest {

    @DisplayName("시간 단위 이용권 선택은 '1'을 입력해야 한다.")
    @Test
    void getHourlyTypeFromUserAction() {
        // given
        InputStream inputStream = new ByteArrayInputStream("1".getBytes());
        System.setIn(inputStream);

        InputHandler inputHandler = new InputHandler();

        // when
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

        // then
        assertThat(passType).isEqualTo(StudyCafePassType.HOURLY);
    }

    @DisplayName("주 단위 이용권 선택은 '2'을 입력해야 한다.")
    @Test
    void getWeeklyTypeFromUserAction() {
        // given
        InputStream inputStream = new ByteArrayInputStream("2".getBytes());
        System.setIn(inputStream);

        InputHandler inputHandler = new InputHandler();

        // when
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

        // then
        assertThat(passType).isEqualTo(StudyCafePassType.WEEKLY);
    }

    @DisplayName("고정성 이용권 선택은 '3'을 입력해야 한다.")
    @Test
    void getFixedTypeFromUserAction() {
        // given
        InputStream inputStream = new ByteArrayInputStream("3".getBytes());
        System.setIn(inputStream);

        InputHandler inputHandler = new InputHandler();

        // when
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

        // then
        assertThat(passType).isEqualTo(StudyCafePassType.FIXED);
    }

    @DisplayName("이용권 선택 시, '1', '2', '3' 이외할 경우 예외를 발생한다.")
    @Test
    void throwExceptionWithUnknownUserAction() {
        // given
        InputStream inputStream = new ByteArrayInputStream("4".getBytes());
        System.setIn(inputStream);

        InputHandler inputHandler = new InputHandler();

        // when - then
        assertThatThrownBy(inputHandler::getPassTypeSelectingUserAction)
                .isInstanceOf(AppException.class);
    }

    /**
     * 이용권 번호 외 입력 시, AppException으로 처리할 것인지?
     */
    @DisplayName("스터디 카페 이용권 중 어떤 이용권을 선택할 것인지 입력을 받는다.")
    @Test
    void getSelectPass() {
        // given
        InputStream inputStream = new ByteArrayInputStream("2".getBytes());
        System.setIn(inputStream);

        List<StudyCafeSeatPass> seatPasses = List.of(
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 40000, 0.1),
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 8, 80000, 0.2),
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 12, 120000, 0.3)
        );

        InputHandler inputHandler = new InputHandler();

        // when
        StudyCafeSeatPass selectPass = inputHandler.getSelectPass(seatPasses);

        // then
        assertThat(selectPass)
                .extracting("passType", "duration", "price", "discountRate")
                .contains(StudyCafePassType.HOURLY, 8, 80000, 0.2);
    }

}