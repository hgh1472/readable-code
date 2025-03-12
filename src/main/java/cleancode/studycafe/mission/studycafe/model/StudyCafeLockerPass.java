package cleancode.studycafe.mission.studycafe.model;

public class StudyCafeLockerPass implements Pass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;

    private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafeLockerPass(passType, duration, price);
    }

    @Override
    public boolean isHourUnit() {
        return passType == StudyCafePassType.HOURLY;
    }

    @Override
    public boolean isWeekUnit() {
        return passType != StudyCafePassType.HOURLY;
    }

    @Override
    public boolean isDiscountable() {
        return false;
    }

    @Override
    public boolean isLockerPass() {
        return true;
    }

    @Override
    public int calculateDiscountPrice() {
        return 0;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

}
