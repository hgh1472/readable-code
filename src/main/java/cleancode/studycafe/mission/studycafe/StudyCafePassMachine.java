package cleancode.studycafe.mission.studycafe;

import cleancode.studycafe.mission.studycafe.config.StudyCafeConfig;
import cleancode.studycafe.mission.studycafe.exception.AppException;
import cleancode.studycafe.mission.studycafe.io.InputHandler;
import cleancode.studycafe.mission.studycafe.io.OutputHandler;
import cleancode.studycafe.mission.studycafe.io.PassReader;
import cleancode.studycafe.mission.studycafe.model.*;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final PassReader passReader;

    public StudyCafePassMachine(StudyCafeConfig studyCafeConfig) {
        this.inputHandler = studyCafeConfig.getInputHandler();
        this.outputHandler = studyCafeConfig.getOutputHandler();
        this.passReader = studyCafeConfig.getPassReader();
    }

    public void run() {
        try {
            showStartComments();

            Order order = createOrder();

            outputHandler.showPassOrderSummary(order);
        } catch (AppException e) {
            outputHandler.showError(e);
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void showStartComments() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

    private Order createOrder() {
        Order order = Order.create();

        StudyCafePass selectedPass = getSelectedPass();
        order.addPass(selectedPass);

        if (selectedPass.isAvailableForLocker()) {
            Optional<StudyCafeLockerPass> lockerPass = getLockerPassFor(selectedPass);
            lockerPass.ifPresent(order::addPass);
        }

        return order;
    }

    private StudyCafePass getSelectedPass() {
        StudyCafePassType selectedType = getSelectedPassTypeFromUser();
        List<StudyCafePass> availablePasses = findAvailablePassesFrom(selectedType);
        return getSelectedPassFrom(availablePasses);
    }

    private Optional<StudyCafeLockerPass> getLockerPassFor(StudyCafePass selectedPass) {
        Optional<StudyCafeLockerPass> availableLocker = findAvailableLockerFor(selectedPass);
        if (availableLocker.isPresent()) {
            return askLockerUsage(availableLocker.get());
        }
        return Optional.empty();
    }

    private StudyCafePassType getSelectedPassTypeFromUser() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private List<StudyCafePass> findAvailablePassesFrom(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> existingStudyCafePasses = passReader.readStudyCafePasses();
        StudyCafePasses studyCafePasses = StudyCafePasses.of(existingStudyCafePasses);
        return studyCafePasses.extractBy(studyCafePassType);

    }

    private StudyCafePass getSelectedPassFrom(List<StudyCafePass> hourlyPasses) {
        outputHandler.showPassListForSelection(hourlyPasses);
        return inputHandler.getSelectedPass(hourlyPasses);
    }

    private Optional<StudyCafeLockerPass> findAvailableLockerFor(StudyCafePass selectedPass) {
        List<StudyCafeLockerPass> existingLockerPasses = passReader.readLockerPasses();
        return existingLockerPasses.stream()
                .filter(selectedPass::isMatchLocker)
                .findFirst();
    }

    private Optional<StudyCafeLockerPass> askLockerUsage(StudyCafeLockerPass availableLocker) {
        boolean doesUserUseLocker = getLockerUsageFromUser(availableLocker);
        if (doesUserUseLocker) {
            return Optional.of(availableLocker);
        }
        return Optional.empty();
    }

    private boolean getLockerUsageFromUser(StudyCafeLockerPass lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }

}
