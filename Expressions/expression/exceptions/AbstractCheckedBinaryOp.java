package expression.exceptions;

import java.util.Objects;
import expression.*;

public abstract class AbstractCheckedBinaryOp implements GeneralExpression {
    protected final GeneralExpression first;
    protected final GeneralExpression second;
    public AbstractCheckedBinaryOp(GeneralExpression first, GeneralExpression second) {
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
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(first).append(" ")
                .append(getOperation()).append(" ")
                .append(second).append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            AbstractCheckedBinaryOp cast = (AbstractCheckedBinaryOp) o;
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
