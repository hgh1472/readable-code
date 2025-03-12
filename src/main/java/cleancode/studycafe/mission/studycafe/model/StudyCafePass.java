package cleancode.studycafe.mission.studycafe.model;

public class StudyCafePass implements Pass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    @Override
    public int calculateDiscountPrice() {
        return (int) (this.price * discountRate);
    }

    public boolean isAvailableForLocker() {
        return passType == StudyCafePassType.FIXED;
    }

    @Override
    public boolean isDiscountable() {
        return discountRate != 0;
    }

    @Override
    public boolean isLockerPass() {
        return false;
    }

    public boolean isMatchLocker(StudyCafeLockerPass lockerPass) {
        return duration == lockerPass.getDuration();
    }

    public boolean isHourUnit() {
        return passType == StudyCafePassType.HOURLY;
    }

    public boolean isWeekUnit() {
        return passType == StudyCafePassType.WEEKLY || passType == StudyCafePassType.FIXED;
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
