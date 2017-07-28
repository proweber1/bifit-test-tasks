import java.math.BigInteger;

public class Application {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please, enter the number");
            return;
        }

        calculateFactorialWithPrinting(args[0]);
    }

    private static void calculateFactorialWithPrinting(String numberFromProgramInput) {
        final int numberForFactorialCalculate = Integer.valueOf(numberFromProgramInput);
        final BigInteger result = calculateFactorial(numberForFactorialCalculate);

        System.out.println(result);
    }

    private static BigInteger calculateFactorial(int input) {
        /*
        В задании пример с маленьким числом, но я подумал что при тестировании
        может ввестись большое число (вычисли мне факториал 10_000), это решение
        вычислит
         */
        BigInteger fact = BigInteger.ONE;
        for (int i = 1; i <= input; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }
}
