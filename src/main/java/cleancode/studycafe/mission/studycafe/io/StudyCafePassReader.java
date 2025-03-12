package cleancode.studycafe.mission.studycafe.io;

import cleancode.studycafe.mission.studycafe.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.studycafe.model.StudyCafePass;

import java.util.List;

public interface StudyCafePassReader {

    List<StudyCafePass> readStudyCafePasses();

    List<StudyCafeLockerPass> readLockerPasses();
}
