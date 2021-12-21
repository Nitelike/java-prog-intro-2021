package expression;

import java.math.BigInteger;

public class Variable implements AbstractExpression, ToMiniString {
    private final String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (var.equals("x")) {
            return x;
        } else if (var.equals("y")) {
            return y;
        } else if (var.equals("z")) {
            return z;
        } else {
            throw new AssertionError("Unknown variable name: " + var);
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean needAdditionalBrackets() {
        return false;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return this.toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }
}
