package cleancode.studycafe.mission.studycafe.io;

import cleancode.studycafe.mission.studycafe.model.Order;
import cleancode.studycafe.mission.studycafe.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.studycafe.model.StudyCafePass;

import java.util.List;

public interface OutputHandler {

    void showWelcomeMessage();

    void showAnnouncement();

    void askPassTypeSelection();

    void showPassListForSelection(List<StudyCafePass> passes);

    void askLockerPass(StudyCafeLockerPass lockerPass);

    void showPassOrderSummary(Order order);

    void showSimpleMessage(String message);

    void showError(Exception e);

}
