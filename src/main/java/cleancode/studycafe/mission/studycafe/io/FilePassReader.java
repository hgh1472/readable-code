package cleancode.studycafe.mission.studycafe.io;

import cleancode.studycafe.mission.studycafe.model.StudyCafeLockerPass;
import cleancode.studycafe.mission.studycafe.model.StudyCafePass;
import cleancode.studycafe.mission.studycafe.model.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilePassReader implements PassReader {

    private static final String PASS_LIST_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    private static final String LOCKER_PATH = "src/main/resources/cleancode/studycafe/locker.csv";

    private static final int TYPE_INDEX = 0;
    private static final int DURATION_INDEX = 1;
    private static final int PRICE_INDEX = 2;
    private static final int DISCOUNT_RATE_INDEX = 3;

    @Override
    public List<StudyCafePass> readStudyCafePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PASS_LIST_PATH));
            List<StudyCafePass> studyCafePasses = new ArrayList<>();
            for (String line : lines) {
                StudyCafePass studyCafePass = extractStudyCafePassFrom(line);
                studyCafePasses.add(studyCafePass);
            }

            return studyCafePasses;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    @Override
    public List<StudyCafeLockerPass> readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOCKER_PATH));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                StudyCafeLockerPass lockerPass = extractLockerPassFrom(line);
                lockerPasses.add(lockerPass);
            }

            return lockerPasses;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    private StudyCafePass extractStudyCafePassFrom(String line) {
        String[] values = line.split(",");

        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[TYPE_INDEX]);
        int duration = Integer.parseInt(values[DURATION_INDEX]);
        int price = Integer.parseInt(values[PRICE_INDEX]);
        double discountRate = Double.parseDouble(values[DISCOUNT_RATE_INDEX]);

        return StudyCafePass.of(studyCafePassType, duration, price, discountRate);
    }

    private StudyCafeLockerPass extractLockerPassFrom(String line) {
        String[] values = line.split(",");

        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[TYPE_INDEX]);
        int duration = Integer.parseInt(values[DURATION_INDEX]);
        int price = Integer.parseInt(values[PRICE_INDEX]);

        return StudyCafeLockerPass.of(studyCafePassType, duration, price);
    }

}
