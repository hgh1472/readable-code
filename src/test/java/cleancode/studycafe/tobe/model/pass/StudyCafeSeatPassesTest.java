package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class StudyCafeSeatPassesTest {


    @DisplayName("스터디 카페 이용권 중 해당하는 타입의 이용권들을 찾는다.")
    @Test
    void findPassBy() {
        // given
        StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(List.of(
                StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 100000, 0.15),
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 12, 100000, 0.15),
                StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 100000, 0.15)
        ));

        // when
        List<StudyCafeSeatPass> fixedPasses = studyCafeSeatPasses.findPassBy(StudyCafePassType.FIXED);

        // then
        assertThat(fixedPasses).hasSize(1)
                .extracting("passType", "duration", "price", "discountRate")
                .containsExactlyInAnyOrder(tuple(StudyCafePassType.FIXED, 12, 100000, 0.15));
    }

}