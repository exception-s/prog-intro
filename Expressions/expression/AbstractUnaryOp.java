package expression;

import java.util.Objects;

public abstract class AbstractUnaryOp implements GeneralExpression {
    protected final GeneralExpression element;
    public AbstractUnaryOp(GeneralExpression element) {
        this.element = element;
    }
    protected abstract String getOperation();
    protected abstract int calculate(int x);

    @Override
    public int evaluate(int x) {
        return calculate(element.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(element.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getOperation()).append("(").append(element).append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUnaryOp that = (AbstractUnaryOp) o;
        return Objects.equals(element, that.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, getOperation());
    }
}
