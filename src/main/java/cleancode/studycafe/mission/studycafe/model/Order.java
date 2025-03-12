package cleancode.studycafe.mission.studycafe.model;

import cleancode.studycafe.mission.studycafe.exception.AppException;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Pass> passes;

    private Order(List<Pass> passes) {
        this.passes = passes;
    }

    public static Order create() {
        return new Order(new ArrayList<>());
    }

    public void addPass(Pass pass) {
        passes.add(pass);
    }

    public int calculateDiscountPrice() {
        return passes.stream()
                .filter(Pass::isDiscountable)
                .mapToInt(Pass::calculateDiscountPrice)
                .sum();
    }

    public int calculateTotalPrice() {
        int notDiscountedPrice = passes.stream()
                .mapToInt(Pass::getPrice)
                .sum();

        return notDiscountedPrice - calculateDiscountPrice();
    }

    public boolean doesPurchaseLocker() {
        return passes.stream()
                .anyMatch(Pass::isLockerPass);
    }

    public boolean isDiscountable() {
        return passes.stream()
                .anyMatch(Pass::isDiscountable);
    }

    public Pass getStudyCafePass() {
        return passes.stream()
                .filter(pass -> !pass.isLockerPass())
                .findFirst()
                .orElseThrow(() -> new AppException("스터디카페 이용권이 없습니다."));
    }

    public Pass getLockerPass() {
        return passes.stream()
                .filter(Pass::isLockerPass)
                .findFirst()
                .orElseThrow(() -> new AppException("사물함 이용권이 없습니다."));
    }
}
