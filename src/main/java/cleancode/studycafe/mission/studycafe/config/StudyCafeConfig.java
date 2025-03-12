package cleancode.studycafe.mission.studycafe.config;

import cleancode.studycafe.mission.studycafe.io.InputHandler;
import cleancode.studycafe.mission.studycafe.io.OutputHandler;
import cleancode.studycafe.mission.studycafe.io.StudyCafePassReader;

public class StudyCafeConfig {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafePassReader studyCafeHandler;

    public StudyCafeConfig(InputHandler inputHandler, OutputHandler outputHandler, StudyCafePassReader studyCafeHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafeHandler = studyCafeHandler;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public StudyCafePassReader getStudyCafeHandler() {
        return studyCafeHandler;
    }

}
