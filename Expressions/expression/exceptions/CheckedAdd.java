package expression.exceptions;

import expression.GeneralExpression;

public class CheckedAdd extends AbstractCheckedBinaryOp {
    public CheckedAdd(GeneralExpression first, GeneralExpression second) {
        super(first, second);
    }

    @Override
    protected String getOperation() {
        return "+";
    }

    @Override
    protected int calculate(int varOne, int varTwo) {
        if (varOne > 0 && Integer.MAX_VALUE - varOne < varTwo) {
            throw new OverflowedAddException("overflow by: " + varOne + ", " + varTwo);
        } else if (varOne < 0 && Integer.MIN_VALUE - varOne > varTwo) {
            throw new OverflowedAddException("overflow by: " + varOne + ", " + varTwo);
        } else {
            return varOne + varTwo;
        }
    }
}
