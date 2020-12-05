package maintenance;

import enums.ErrorMessage;
import enums.GameProcess;
import racingcar.Car;

import java.util.List;

public class Mechanic {

    private int numberOfNamesOfCarsWithForeAndAftBlank =
            GameProcess.INITIAL_EXCEPTION_NUMBER.getValue();
    private int numberOfNamesOfCarsOverMaxLength =
            GameProcess.INITIAL_EXCEPTION_NUMBER.getValue();

    public Car getCarWithValidName(String nameOfCar) {
        checkNameOfCarIsExceptonal(nameOfCar);
        nameOfCar = shortenNameLengthToMax(nameOfCar.trim());
        if (isNameBlank(nameOfCar)) {
            return null;
        }
        return new Car(nameOfCar);
    }

    private void checkNameOfCarIsExceptonal(String nameOfCar) {
        if (isNameWithForeAndAftBlank(nameOfCar)) {
            numberOfNamesOfCarsWithForeAndAftBlank++;
        }
        if (isLengthOfNameOverMax(nameOfCar.trim())) {
            numberOfNamesOfCarsOverMaxLength++;
        }
    }

    private boolean isNameWithForeAndAftBlank(String nameOfCar) {
        return isStartsWithBlank(nameOfCar) || isEndsWithBlank(nameOfCar);
    }

    private boolean isLengthOfNameOverMax(String nameOfCar) {
        return nameOfCar.length() > GameProcess.MAXIMUM_NAME_LENGTH.getValue();
    }

    private boolean isStartsWithBlank(String nameOfCar) {
        return nameOfCar.startsWith(" ");
    }

    private boolean isEndsWithBlank(String nameOfCar) {
        return nameOfCar.endsWith(" ");
    }

    public String shortenNameLengthToMax(String nameOfCar) {
        if (isLengthOfNameOverMax(nameOfCar)) {
            return nameOfCar.substring(0, GameProcess.MAXIMUM_NAME_LENGTH.getValue());
        }
        return nameOfCar;
    }

    private boolean isNameBlank(String nameOfCar) {
        return nameOfCar.equals("");
    }

    public void noticeErrors(String[] namesOfCarsArray, List<Car> carList) {
        noticeNameTrimmed();
        noticeShortenName();
        noticeBlankNameRemoved(namesOfCarsArray, carList);
    }

    public void noticeNameTrimmed() {
        if (numberOfNamesOfCarsWithForeAndAftBlank
                > GameProcess.INITIAL_EXCEPTION_NUMBER.getValue()) {
            System.err.println(ErrorMessage.NAME_WITH_FORE_AND_AFT_BLANK.getMessage());
        }
    }

    public void noticeShortenName() {
        if (numberOfNamesOfCarsOverMaxLength
                > GameProcess.INITIAL_EXCEPTION_NUMBER.getValue()) {
            System.err.println(ErrorMessage.OVER_MAXIMUM_NAME_LENGTH.getMessage());
        }
    }

    public void noticeBlankNameRemoved(String[] namesOfCarsArray, List<Car> carList) {
        if (isBlankNameInputted(namesOfCarsArray, carList)) {
            System.err.println(ErrorMessage.BLANK_NAME.getMessage());
        }
    }

    private boolean isBlankNameInputted(String[] namesOfCarsArray, List<Car> carList) {
        return namesOfCarsArray.length != carList.size();
    }

    public boolean isNumberFormat(String turnsToTry) {
        try {
            Integer.parseInt(turnsToTry);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public int getAbsoluteValue(String turnsToTry) {
        int turnsToTryInt = Integer.parseInt(turnsToTry);
        if (isNegativeNumber(turnsToTryInt)) {
            System.err.println(ErrorMessage.NEGATIVE_NUMBER.getMessage());
            return Math.abs(turnsToTryInt);
        }
        return turnsToTryInt;
    }

    private boolean isNegativeNumber(int turnsToTryInt) {
        return turnsToTryInt < 0;
    }

    public void setNumberOfNamesOfCarsWithForeAndAftBlank(
            int numberOfNamesOfCarsWithForeAndAftBlank) {
        this.numberOfNamesOfCarsWithForeAndAftBlank = numberOfNamesOfCarsWithForeAndAftBlank;
    }

    public void setNumberOfNamesOfCarsOverMaxLength(int numberOfNamesOfCarsOverMaxLength) {
        this.numberOfNamesOfCarsOverMaxLength = numberOfNamesOfCarsOverMaxLength;
    }
}
