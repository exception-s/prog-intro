package expression;

import java.util.Objects;

public class Const implements GeneralExpression {
    private final int C;
    public Const(int c) {
        this.C = c;
    }

    @Override
    public String toString() {
        return Integer.toString(C);
    }
    @Override
    public int evaluate(int x) {
        return C;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return C;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return C == aConst.C;
    }

    @Override
    public int hashCode() {
        return Objects.hash(C);
    }
}
