package cleancode.studycafe.mission.studycafe.model;

public interface Pass {
    int getPrice();

    int getDuration();

    boolean isHourUnit();

    boolean isWeekUnit();

    boolean isDiscountable();

    boolean isLockerPass();

    int calculateDiscountPrice();

}
