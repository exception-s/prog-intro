package expression.exceptions;

import expression.GeneralExpression;

public class CheckedMultiply extends AbstractCheckedBinaryOp {
    public CheckedMultiply(GeneralExpression first, GeneralExpression second) {
        super(first, second);
    }

    @Override
    protected String getOperation() {
        return "*";
    }

    @Override
    protected int calculate(int varOne, int varTwo) {
        if (varOne != 0 && varTwo != 0) {
            if (varOne == Integer.MIN_VALUE && varTwo == 1 || varOne == 1 && varTwo == Integer.MIN_VALUE) {
                return varOne * varTwo;
            }
            if (varOne == -1 && varTwo == Integer.MIN_VALUE || varTwo == -1 && varOne == Integer.MIN_VALUE) {
                throw new OverflowedMultiplyException("overflow by: " + varOne + ", " + varTwo);
            } else if (varOne * varTwo == Integer.MIN_VALUE && Integer.MIN_VALUE / varTwo == varOne) {
                return Integer.MIN_VALUE;
            } else if (varOne == Integer.MIN_VALUE || varTwo == Integer.MIN_VALUE) {
                throw new OverflowedMultiplyException("overflow by: " + varOne + ", " + varTwo);
            } else if (Integer.MAX_VALUE / Math.abs(varOne) < Math.abs(varTwo)) {
                throw new OverflowedMultiplyException("overflow  by: " + varOne + ", " + varTwo);
            } else {
                return varOne * varTwo;
            }
        } else {
            return 0;
        }
    }
}
