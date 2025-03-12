package cleancode.studycafe.mission.studycafe.io;

import cleancode.studycafe.asis.exception.AppException;
import cleancode.studycafe.mission.studycafe.model.Order;
import cleancode.studycafe.mission.studycafe.model.Pass;
import cleancode.studycafe.mission.studycafe.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.studycafe.model.StudyCafePass;

import java.util.List;

public class ConsoleOutputHandler implements OutputHandler {

    @Override
    public void showWelcomeMessage() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    @Override
    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        separateLine();
    }

    @Override
    public void askPassTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        System.out.println("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    @Override
    public void showPassListForSelection(List<StudyCafePass> passes) {
        separateLine();
        System.out.println("이용권 목록");
        for (int index = 0; index < passes.size(); index++) {
            StudyCafePass pass = passes.get(index);
            System.out.println(indicateNumber(index) + extractPassInformation(pass));
        }
    }

    @Override
    public void askLockerPass(StudyCafeLockerPass lockerPass) {
        separateLine();
        String askMessage = String.format(
                "사물함을 이용하시겠습니까? (%s)",
                extractPassInformation(lockerPass)
        );

        System.out.println(askMessage);
        System.out.println("1. 예 | 2. 아니오");
    }

    @Override
    public void showPassOrderSummary(Order order) {
        showSummaryHeader();
        showPassInformation(order);
        if (order.doesPurchaseLocker()) {
            showLockerInformation(order.getLockerPass());
        }

        if (order.isDiscountable()) {
            showDiscountPrice(order);
        }

        showTotalPrice(order);
    }

    private static String indicateNumber(int index) {
        return String.format("%s. ", index + 1);
    }

    private static void showTotalPrice(Order order) {
        int totalPrice = order.calculateTotalPrice();
        System.out.println("총 결제 금액: " + totalPrice + "원");
        separateLine();
    }

    private static void showSummaryHeader() {
        separateLine();
        System.out.println("이용 내역");
    }

    private void showPassInformation(Order order) {
        String passInformation = extractPassInformation(order.getStudyCafePass());
        System.out.println("이용권: " + passInformation);
    }

    private void showLockerInformation(Pass lockerPass) {
        String lockerInformation = extractPassInformation(lockerPass);
        System.out.println("사물함: " + lockerInformation);
    }

    private static void showDiscountPrice(Order order) {
        int discountPrice = order.calculateDiscountPrice();
        System.out.println("이벤트 할인 금액: " + discountPrice + "원");
    }

    private String extractPassInformation(Pass pass) {
        int duration = pass.getDuration();
        int price = pass.getPrice();
        if (pass.isHourUnit()) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (pass.isWeekUnit()) {
            return String.format("%s주권 - %d원", duration, price);
        }
        throw new AppException("지원되지 않는 이용권입니다.");
    }

    @Override
    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    private static void separateLine() {
        System.out.println();
    }
}
