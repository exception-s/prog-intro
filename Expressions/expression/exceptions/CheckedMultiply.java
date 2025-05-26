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
        if (varOne == 0 || varTwo == 0) {
            return 0;
        }
        if ((varOne == -1 && varTwo == Integer.MIN_VALUE) || (varTwo == -1 && varOne == Integer.MIN_VALUE)) {
            throw new OverflowedMultiplyException("overflow by: " + varOne + ", " + varTwo);
        }
        int result = varOne * varTwo;
        if (result / varTwo != varOne) {
            throw new OverflowedMultiplyException("overflow by: " + varOne + ", " + varTwo);
        }
        return result;
    }
}
