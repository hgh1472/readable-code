package cleancode.studycafe.mission.studycafe.io;

import cleancode.studycafe.mission.studycafe.model.StudyCafePass;
import cleancode.studycafe.mission.studycafe.model.StudyCafePassType;

import java.util.List;

public interface InputHandler {

    StudyCafePassType getPassTypeSelectingUserAction();

    StudyCafePass getSelectedPass(List<StudyCafePass> passes);

    boolean getLockerSelection();
}
