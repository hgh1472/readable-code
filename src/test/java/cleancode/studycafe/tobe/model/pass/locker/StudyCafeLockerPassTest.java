package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeLockerPassTest {

    @DisplayName("이용권 타입이 같은지 확인한다.")
    @CsvSource({"FIXED, true", "HOURLY, false", "WEEKLY, false"})
    @ParameterizedTest(name = "FIXED 타입과 {0} 타입이 같은지 : {1}")
    void isSamePassType(StudyCafePassType passType, boolean expected) {
        // given
        StudyCafeLockerPass fixedLockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 40000);

        // when
        boolean isSamePassType = fixedLockerPass.isSamePassType(passType);

        // then
        assertThat(isSamePassType).isEqualTo(expected);
    }

    @DisplayName("이용 기간이 같은지 확인한다.")
    @Test
    void isSameDuration() {
        // given
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 40000);

        // when
        boolean isSameDuration = lockerPass.isSameDuration(12);

        // then
        assertThat(isSameDuration).isTrue();
    }

}