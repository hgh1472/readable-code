package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassTest {

    @DisplayName("시간 단위 이용권과 주 단위 이용권은 사물함을 사용할 수 없다.")
    @CsvSource({"HOURLY, true", "WEEKLY, true", "FIXED, false"})
    @ParameterizedTest(name = "{0} 타입 이용권의 사물함 사용 불가능 여부 : {1}")
    void cannotUseLocker(StudyCafePassType passType, boolean expected) {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(passType, 12, 400000, 0.15);

        // when
        boolean cannotUseLocker = seatPass.cannotUseLocker();

        // then
        assertThat(cannotUseLocker).isEqualTo(expected);
    }

    @DisplayName("좌석이용권의 타입과 기간이 사물함 이용권의 타입과 기간이랑 같은지 확인한다.")
    @CsvSource({"FIXED, 12, FIXED, 12, true", "FIXED, 12, WEEKLY, 12, false", "FIXED, 12, FIXED, 2, false"})
    @ParameterizedTest(name = "좌석 이용권({0}, {1}) 사물함 이용권({2}, {3}) : {4}")
    void isSameDurationType(StudyCafePassType seatPassType, int seatDuration, StudyCafePassType lockerPassType, int lockerDuration, boolean expected) {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(seatPassType, seatDuration, 400000, 0.15);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(lockerPassType, lockerDuration, 40000);

        // when
        boolean isSameDurationType = seatPass.isSameDurationType(lockerPass);

        // then
        assertThat(isSameDurationType).isEqualTo(expected);
    }

    @DisplayName("이용권 타입이 같은지 확인한다.")
    @CsvSource({"FIXED, true", "HOURLY, false", "WEEKLY, false"})
    @ParameterizedTest(name = "FIXED 타입과 {0} 타입이 같은지 : {1}")
    void isSamePassType(StudyCafePassType passType, boolean expected) {
        // given
        StudyCafeSeatPass fixedPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 400000, 0.15);

        // when
        boolean isSamePassType = fixedPass.isSamePassType(passType);

        // then
        assertThat(isSamePassType).isEqualTo(expected);
    }

    @DisplayName("스터디 카페 이용권의 할인 금액을 반환한다.")
    @Test
    void getDiscountPrice() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 100000, 0.15);

        // when
        int discountPrice = seatPass.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo(15000);
    }

}