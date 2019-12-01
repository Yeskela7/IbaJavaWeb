package calc;

import java.util.Optional;

public class Calc {

    public static String calc(String num1, String num2, String operation) {
        return do_op(num1, num2, operation).map(r -> String.format("%d", r)).orElse("Error");
    }

    private static Optional<Integer> do_op(String num1, String num2, String operation) {
        Optional<Integer> n1 = convertToInt(num1);
        Optional<Integer> n2 = convertToInt(num2);
        Optional<String> op = wrap(operation);

        return op.flatMap(o -> n1.flatMap(op1 -> n2.flatMap(op2 -> {
            switch (o) {
                case "+":
                    return Optional.of(op1 + op2);
                case "-":
                    return Optional.of(op1 - op2);
                case "*":
                    return Optional.of(op1 * op2);
                case "/":
                    return divide(op1, op2);
            }
            return Optional.empty();
        })));
    }

    private static Optional<Integer> divide(Integer op1, Integer op2) {
        if (op2 == 2) return Optional.empty();
        return Optional.of(op1 / op2);
    }

    private static Optional<String> wrap(String operation) {
        return Optional.ofNullable(operation);
    }

    private static Optional<Integer> convertToInt(String num) {
        try {
            return Optional.of(Integer.valueOf(num));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
