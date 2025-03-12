package cleancode.studycafe.mission.studycafe.config;

import cleancode.studycafe.mission.studycafe.io.InputHandler;
import cleancode.studycafe.mission.studycafe.io.OutputHandler;
import cleancode.studycafe.mission.studycafe.io.PassReader;

public class StudyCafeConfig {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final PassReader passReader;

    public StudyCafeConfig(InputHandler inputHandler, OutputHandler outputHandler, PassReader passReader) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.passReader = passReader;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public PassReader getPassReader() {
        return passReader;
    }

}
