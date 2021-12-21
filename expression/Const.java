package expression;

import java.math.BigInteger;

public class Const implements AbstractExpression, ToMiniString {
    private final BigInteger x;

    public Const(int x) {
        this.x = BigInteger.valueOf(x);
    }

    public Const(BigInteger x) {
        this.x = x;
    }

    @Override
    public int evaluate(int x) {
        return this.x.intValue();
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return this.x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.x.intValue();
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
        return x.toString();
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
        return x.intValue();
    }
}
