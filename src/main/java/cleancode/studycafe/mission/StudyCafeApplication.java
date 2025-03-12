package cleancode.studycafe.mission;


import cleancode.studycafe.mission.studycafe.StudyCafePassMachine;
import cleancode.studycafe.mission.studycafe.config.StudyCafeConfig;
import cleancode.studycafe.mission.studycafe.io.ConsoleInputHandler;
import cleancode.studycafe.mission.studycafe.io.ConsoleOutputHandler;
import cleancode.studycafe.mission.studycafe.io.FilePassReader;

public class StudyCafeApplication {

    public StudyCafeApplication() {
    }

    public static void main(String[] args) {
        StudyCafeConfig studyCafeConfig = new StudyCafeConfig(
                new ConsoleInputHandler(),
                new ConsoleOutputHandler(),
                new FilePassReader()
        );

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(studyCafeConfig);
        studyCafePassMachine.run();
    }

}
