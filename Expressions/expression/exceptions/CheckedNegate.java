package expression.exceptions;

import expression.GeneralExpression;

public class CheckedNegate extends AbstractCheckedUnaryOp {
    public CheckedNegate(GeneralExpression element) {
        super(element);
    }

    @Override
    protected String getOperation() {
        return "-";
    }

    @Override
    protected int calculate(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowedNegateException("overflow by: " + element);
        } else {
            return -x;
        }
    }
}
