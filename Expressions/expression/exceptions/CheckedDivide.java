package expression.exceptions;

import expression.GeneralExpression;

public class CheckedDivide extends AbstractCheckedBinaryOp {
    public CheckedDivide(GeneralExpression first, GeneralExpression second) {
        super(first, second);
    }

    @Override
    protected String getOperation() {
        return "/";
    }

    @Override
    protected int calculate(int varOne, int varTwo) {
        if (varTwo == 0) {
            throw new DivisionByZeroException("division by zero");
        } else if (varOne == Integer.MIN_VALUE && varTwo == -1) {
            throw new OverflowedDivisionException("overflow by: " + varOne + ", " + varTwo);
        }
        return varOne / varTwo;
    }
}
