package cleancode.studycafe.mission.studycafe.io;

import cleancode.studycafe.mission.studycafe.exception.AppException;
import cleancode.studycafe.mission.studycafe.model.StudyCafePass;
import cleancode.studycafe.mission.studycafe.model.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String HOURLY_TYPE = "1";
    private static final String WEEKLY_TYPE = "2";
    private static final String FIXED_TYPE = "3";

    @Override
    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();

        if (HOURLY_TYPE.equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if (WEEKLY_TYPE.equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if (FIXED_TYPE.equals(userInput)) {
            return StudyCafePassType.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    @Override
    public StudyCafePass getSelectedPass(List<StudyCafePass> passes) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.get(selectedIndex);
    }

    @Override
    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return HOURLY_TYPE.equals(userInput);
    }

}
