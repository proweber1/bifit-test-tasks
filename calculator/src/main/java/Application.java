public class Application {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please, enter expression");
            return;
        }

        calculateExpression(args[0]);
    }

    private static void calculateExpression(String expression) {
        try {
            final int result = new ExpressionCalculator(expression).calculate();
            System.out.println(result);
        } catch (UnsupportedOperatorException | OperandsCountException e) {
            System.err.println(e.getMessage());
        }
    }
}
