package expression;

import java.util.Objects;

public abstract class AbstractBinaryOp implements GeneralExpression {
    protected final GeneralExpression first;
    protected final GeneralExpression second;
    public AbstractBinaryOp(GeneralExpression first, GeneralExpression second) {
        this.first = first;
        this.second = second;
    }
    protected abstract String getOperation();
    protected abstract int calculate(int varOne, int varTwo);

    @Override
    public int evaluate(int x) {
        return calculate(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + first + " " +
                getOperation() + " " +
                second + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            AbstractBinaryOp cast = (AbstractBinaryOp) o;
            return cast.first.equals(first) &&
                    cast.second.equals(second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, getOperation(), second, getClass());
    }
}
