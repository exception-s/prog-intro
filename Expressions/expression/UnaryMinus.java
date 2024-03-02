package expression;

public class UnaryMinus extends AbstractUnaryOp {
    public UnaryMinus(GeneralExpression element) {
        super(element);
    }

    @Override
    protected String getOperation() {
        return "-";
    }

    @Override
    protected int calculate(int x) {
        return -x;
    }
}
