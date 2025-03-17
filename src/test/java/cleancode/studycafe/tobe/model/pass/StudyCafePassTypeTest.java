package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassTypeTest {

    @DisplayName("고정석만 사물함 이용이 가능하다.")
    @CsvSource({"HOURLY, false", "WEEKLY, false", "FIXED, true"})
    @ParameterizedTest(name = "{0} 타입 이용권의 사물함 사용 가능 여부 : {1}")
    void isLockerType(StudyCafePassType passType, boolean expected) {
        // when
        boolean isLockerType = passType.isLockerType();

        // then
        assertThat(isLockerType).isEqualTo(expected);
    }


    @DisplayName("시간 단위 이용권과 주 단위 이용권은 사물함 이용이 불가능하다.")
    @CsvSource({"HOURLY, true", "WEEKLY, true", "FIXED, false"})
    @ParameterizedTest(name = "{0} 타입 이용권의 사물함 사용 불가능 여부 : {1}")
    void isNotLockerType(StudyCafePassType passType, boolean expected) {
        // when
        boolean isNotLockerType = passType.isNotLockerType();

        // then
        assertThat(isNotLockerType).isEqualTo(expected);
    }
}