package expression.exceptions;

import expression.GeneralExpression;

public class CheckedTrailingZeroes extends AbstractCheckedUnaryOp {
    public CheckedTrailingZeroes(GeneralExpression element) {
        super(element);
    }

    @Override
    protected String getOperation() {
        return "t0 ";
    }

    @Override
    protected int calculate(int x) {
        return Integer.numberOfTrailingZeros(x);
    }
}
