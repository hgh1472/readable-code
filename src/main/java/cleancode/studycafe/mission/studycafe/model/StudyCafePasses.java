package cleancode.studycafe.mission.studycafe.model;

import java.util.ArrayList;
import java.util.List;

public class StudyCafePasses {

    private final List<StudyCafePass> studyCafePasses;

    private StudyCafePasses(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public static StudyCafePasses of(List<StudyCafePass> studyCafePasses) {
        return new StudyCafePasses(studyCafePasses);
    }

    public List<StudyCafePass> extractBy(StudyCafePassType passType) {
        ArrayList<StudyCafePass> willBeExtracted = new ArrayList<>(studyCafePasses);

        return willBeExtracted.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == passType)
                .toList();
    }

}
