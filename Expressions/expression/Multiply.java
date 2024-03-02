package expression;

public class Multiply extends AbstractBinaryOp {
    public Multiply(GeneralExpression first, GeneralExpression second) {
        super(first, second);
    }

    @Override
    protected String getOperation() {
        return "*";
    }

    @Override
    protected int calculate(int varOne, int varTwo) {
        return varOne * varTwo;
    }
}
