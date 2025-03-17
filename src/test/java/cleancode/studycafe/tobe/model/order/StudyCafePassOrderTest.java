package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassOrderTest {

    @DisplayName("주문의 할인 금액을 반환한다.")
    @Test
    void getDiscountPrice() {
        // given
        StudyCafePassOrder order = StudyCafePassOrder.of(
                StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 100000, 0.15),
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 50000)
        );

        // when
        int discountPrice = order.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo(15000);
    }

    @DisplayName("사물함 이용권이 포함된 주문의 총 결제 금액을 반환한다.")
    @Test
    void getDiscountPriceWithLockerPass() {
        // given
        StudyCafePassOrder order = StudyCafePassOrder.of(
                StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 100000, 0.15),
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 50000)
        );

        // when
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(100000 - 15000 + 50000);
    }

    @DisplayName("사물함 이용권이 없는 주문의 총 결제 금액을 반환한다.")
    @Test
    void getDiscountPriceWithoutLockerPass() {
        // given
        StudyCafePassOrder order = StudyCafePassOrder.of(
                StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 100000, 0.15),
                null
        );

        // when
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(100000 - 15000);
    }

    @DisplayName("사물함 이용권이 포함된 주문의 사물함 이용권을 반환한다.")
    @Test
    void getLockerPass() {
        // given
        StudyCafePassOrder order = StudyCafePassOrder.of(
                StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 100000, 0.15),
                StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 50000)
        );

        // when
        Optional<StudyCafeLockerPass> lockerPass = order.getLockerPass();

        // then
        assertThat(lockerPass).isPresent();
        assertThat(lockerPass.get())
                .extracting("passType", "duration", "price")
                .contains(StudyCafePassType.FIXED, 12, 50000);
    }
}