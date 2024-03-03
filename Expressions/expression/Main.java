package expression;


import expression.exceptions.*;

public class Main {
    public static void main(String[] args) {
        String expression = "-(-2147483648)";
        ExpressionParser parser = new ExpressionParser();
        // System.out.println(parser.parse(expression).toString());
        TripleExpression result = parser.parse(expression);
        System.out.println(result.equals(new CheckedNegate(new Const(Integer.MIN_VALUE))));
        System.out.println('x' + " ".repeat(8) + "f");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ".repeat(8));
            try {
                System.out.println(result.evaluate(i, 0, 0));
            } catch (OverflowException | DivisionByZeroException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
