package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassMachineTest {

    private InputStream inputStream;
    private OutputStream outputStream;

    @DisplayName("시간 단위 이용권을 구매한다.")
    @Test
    void buyHourlySeatPass() {
        // given
        String buyingFirstHourlySeatPass = "1\n1";
        inputUserAction(buyingFirstHourlySeatPass);
        outputMessage();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                new SeatPassFileReader(),
                new LockerPassFileReader()
        );

        // when
        studyCafePassMachine.run();

        // then
        String result = outputStream.toString();
        assertThat(result)
                .contains("이용권:")
                .doesNotContain("사물함:")
                .doesNotContain("이벤트 할인 금액:")
                .contains("총 결제 금액:");
    }

    @DisplayName("할인이 적용되지 않는 주 단위 이용권을 구매한다.")
    @Test
    void buyWeeklySeatPass() {
        // given
        String buyingWeeklySeatPass = "2\n1";
        inputUserAction(buyingWeeklySeatPass);
        outputMessage();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                new SeatPassFileReader(),
                new LockerPassFileReader()
        );

        // when
        studyCafePassMachine.run();

        // then
        String result = outputStream.toString();
        assertThat(result)
                .contains("이용권:")
                .doesNotContain("사물함:")
                .doesNotContain("이벤트 할인 금액:")
                .contains("총 결제 금액:");
    }

    @DisplayName("할인이 적용되는 주 단위 이용권을 구매한다.")
    @Test
    void buyDiscountedWeeklySeatPass() {
        // given
        String buyingDiscountedWeeklyPass = "2\n4";
        inputUserAction(buyingDiscountedWeeklyPass);
        outputMessage();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                new SeatPassFileReader(),
                new LockerPassFileReader()
        );

        // when
        studyCafePassMachine.run();

        // then
        String result = outputStream.toString();
        assertThat(result)
                .contains("이용권:")
                .doesNotContain("사물함:")
                .contains("이벤트 할인 금액:")
                .contains("총 결제 금액:");
    }

    @DisplayName("사물함 이용권 없이 고정석 이용권을 구매한다.")
    @Test
    void buyFixedSeatPass() {
        // given
        String buyingFixedSeatPassWithoutLocker = "3\n1\n2";
        inputUserAction(buyingFixedSeatPassWithoutLocker);
        outputMessage();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                new SeatPassFileReader(),
                new LockerPassFileReader()
        );

        // when
        studyCafePassMachine.run();

        // then
        String result = outputStream.toString();
        assertThat(result)
                .contains("이용권:")
                .doesNotContain("사물함:")
                .contains("이벤트 할인 금액:")
                .contains("총 결제 금액:");
    }

    @DisplayName("사물함 이용권과 함께 고정석 이용권을 구매한다.")
    @Test
    void buyFixedSeatPassWithLockerPass() {
        // given
        String buyingFixedSeatPassWithLocker = "3\n1\n1";
        inputUserAction(buyingFixedSeatPassWithLocker);
        outputMessage();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                new SeatPassFileReader(),
                new LockerPassFileReader()
        );

        // when
        studyCafePassMachine.run();

        // then
        String result = outputStream.toString();
        assertThat(result)
                .contains("이용권:")
                .contains("사물함:")
                .contains("이벤트 할인 금액:")
                .contains("총 결제 금액:");
    }

    private void inputUserAction(String userInput) {
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
    }

    private void outputMessage() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

}