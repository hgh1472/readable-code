package cleancode.studycafe.tobe.io.provider;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SeatPassFileReaderTest {

    @DisplayName("resources/cleancode/studycafe/pass-list.csv 파일에 저장된 스터디 카페 이용권을 불러온다.")
    @Test
    void getSeatPasses() {
        // given
        SeatPassFileReader seatPassFileReader = new SeatPassFileReader();

        // when - then
        assertDoesNotThrow(seatPassFileReader::getSeatPasses);
    }

}