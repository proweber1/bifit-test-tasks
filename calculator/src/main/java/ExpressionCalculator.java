import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class ExpressionCalculator {
    private static final Map<String, CalculatorOperation> CALCULATOR_OPERATIONS = new HashMap<>();

    static {
        for (CalculatorOperation op : CalculatorOperation.values()) {
            CALCULATOR_OPERATIONS.put(op.getOperator(), op);
        }
    }

    private final String expression;

    ExpressionCalculator(String exp) {
        expression = Objects.requireNonNull(exp, "expression must be not null!");
    }

    /**
     * @return Вычисленное выражение
     * @throws UnsupportedOperatorException Это исключение будет брошено если оператор
     *                                      который был указан в выражении неизвестен. если он
     *                                      не является {@literal +-*\/}
     * @throws OperandsCountException       Если количество операндов != 2
     */
    int calculate() throws UnsupportedOperatorException, OperandsCountException {
        final String[] operands = expression.split("\\D");
        final String operator = expression.replaceAll("\\d", "");

        checkCalculateExpressionArguments(operands, operator);
        return calculateParsedOperands(operands, operator);
    }

    /**
     * Проверяет вычисленные операнды и оператор
     * <p>
     * Должно быть два операнда и один оператор который зарезирвирован
     * в {@link ExpressionCalculator#CALCULATOR_OPERATIONS}
     *
     * @param operands Список операндов
     * @param operator Оператор над операндами
     * @throws UnsupportedOperatorException Незивестный оператор
     * @throws OperandsCountException       Количество операндов != 2
     */
    private void checkCalculateExpressionArguments(String[] operands, String operator)
            throws UnsupportedOperatorException, OperandsCountException {
        if (operands.length != 2) {
            throw new OperandsCountException(operands);
        }

        if (!CALCULATOR_OPERATIONS.containsKey(operator)) {
            throw new UnsupportedOperatorException(operator);
        }
    }

    private int calculateParsedOperands(String[] operands, String operator) {
        /*
        По идее, проверяться аргументы должны здесь, но чтобы не распространять
        исключения наверх, чтобы не писать во всех методах throws секцию, я сделать
        проверку вычисленных операндов и оператора в методе calculate
         */
        final CalculatorOperation op = CALCULATOR_OPERATIONS.get(operator);
        return op.calculate(
                Integer.valueOf(operands[0]),
                Integer.valueOf(operands[1]));
    }

    enum CalculatorOperation {
        PLUS("+") {
            int calculate(int operand1, int operand2) {
                return operand1 + operand2;
            }
        },

        SUBTRACT("-") {
            int calculate(int operand1, int operand2) {
                return operand1 - operand2;
            }
        },

        DIVIDE("/") {
            int calculate(int operand1, int operand2) {
                return operand1 / operand2;
            }
        },

        MULTIPLY("*") {
            int calculate(int operand1, int operand2) {
                return operand1 * operand2;
            }
        };

        private final String operator;

        CalculatorOperation(String operator) {
            this.operator = operator;
        }

        public String getOperator() {
            return operator;
        }

        abstract int calculate(int operand1, int operand2);
    }
}
