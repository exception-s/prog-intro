package expression.exceptions;

import base.Selector;
import expression.TripleExpression;

import static expression.exceptions.Operations.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class ExceptionsTest {
    private static final ExpressionParser PARSER = new ExpressionParser();
    private static final Operation TRIPLE = Operations.kind(TripleExpression.KIND, (expr, variables) -> PARSER.parse(expr));

    public static final Selector SELECTOR = Selector.composite(ExceptionsTest.class, ExceptionsTester::new, "easy", "hard")
            .variant("Base", TRIPLE, Operations.ADD, Operations.SUBTRACT, Operations.MULTIPLY, Operations.DIVIDE, Operations.NEGATE)
            .variant("Zeroes", Operations.L_ZEROES, Operations.T_ZEROES)
            .variant("PowLog2", Operations.CHECKED_POW_2, Operations.CHECKED_LOG_2)
            .variant("MinMax", Operations.MIN, Operations.MAX)
            .variant("Shifts", Operations.SHIFT_L, Operations.SHIFT_R, Operations.SHIFT_A)
            .selector();

    private ExceptionsTest() {
    }

    public static void main(final String... args) {
        SELECTOR.main(args);
    }
}
