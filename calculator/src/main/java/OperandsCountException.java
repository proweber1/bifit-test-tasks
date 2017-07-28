import java.util.Arrays;

class OperandsCountException extends Exception {
    OperandsCountException(String[] operands) {
        super("The number of operands must be 2, given: " + Arrays.toString(operands));
    }
}
