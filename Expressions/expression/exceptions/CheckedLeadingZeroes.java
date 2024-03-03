package expression.exceptions;

import expression.GeneralExpression;

public class CheckedLeadingZeroes extends AbstractCheckedUnaryOp {
    public CheckedLeadingZeroes(GeneralExpression element) {
        super(element);
    }

    @Override
    protected String getOperation() {
        return "l0 ";
    }

    @Override
    protected int calculate(int x) {
        return Integer.numberOfLeadingZeros(x);
    }
}
