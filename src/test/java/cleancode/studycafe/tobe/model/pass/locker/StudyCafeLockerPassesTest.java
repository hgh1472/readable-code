package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeLockerPassesTest {

    @DisplayName("스터디카페 이용권에 해당하는 사물함 이용권을 찾는다.")
    @Test
    void findLockerPassBy() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 100000, 0.15);

        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of(
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30000),
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 10, 20000),
                StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 12, 10000)
        ));

        // when
        Optional<StudyCafeLockerPass> findLockerPass = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(findLockerPass).isPresent();
        assertThat(findLockerPass.get())
                .extracting("passType", "duration", "price")
                .contains(StudyCafePassType.FIXED, 12, 30000);
    }

}