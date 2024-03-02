package expression;

import java.util.Objects;

public class Variable implements GeneralExpression {
    private final String var;
    public Variable(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return var;
    }
    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (var) {
            case "x" -> {
                return x;
            }
            case "y" -> {
                return y;
            }
            default -> {
                return z;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(var, variable.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }
}
