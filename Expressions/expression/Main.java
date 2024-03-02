package expression;


import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        String expression = "- -1";
        // test     - -(x + -2147483648) + x * x * - 100
        //   - -(x + -2147483648) + x * (x * - 100)

        //   -((-((x + -2147483648)) + ((x * x) * -100)))

        // x=-1856142235, y=-631464331, z=230294236

        //       expected `-1609149535`,
        //       actual `-2103134935`
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse(expression).toString());
        // System.out.println(parser.parse(expression).evaluate(-1856142235, 0, 0));
    }
}
