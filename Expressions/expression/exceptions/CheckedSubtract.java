package expression.exceptions;

import expression.GeneralExpression;

public class CheckedSubtract extends AbstractCheckedBinaryOp {
    public CheckedSubtract(GeneralExpression first, GeneralExpression second) {
        super(first, second);
    }

    @Override
    protected String getOperation() {
        return "-";
    }

    @Override
    protected int calculate(int varOne, int varTwo) {
        if (varTwo > 0 && Integer.MIN_VALUE + varTwo > varOne) {
            throw new OverflowedSubtractException("overflow by: " + varOne + ", " + varTwo);
        } else if (varTwo < 0 && Integer.MAX_VALUE + varTwo < varOne) {
            throw new OverflowedSubtractException("overflow by: " + varOne + ", " + varTwo);
        } else {
            return varOne - varTwo;
        }
    }
}
