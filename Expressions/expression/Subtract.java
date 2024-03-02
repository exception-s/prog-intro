package expression;

public class Subtract extends AbstractBinaryOp {
    public Subtract(GeneralExpression first, GeneralExpression second) {
        super(first, second);
    }

    @Override
    protected String getOperation() {
        return "-";
    }

    @Override
    protected int calculate(int varOne, int varTwo) {
        return varOne - varTwo;
    }
}
