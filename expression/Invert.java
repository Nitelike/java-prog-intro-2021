package expression;

import java.math.BigInteger;

public class Invert extends AbstractUnaryOperation {
    public Invert(AbstractExpression operand) {
        super("-", operand);
    }

    @Override
    public int calculate(int a) {
        return -a;
    }

    @Override
    public BigInteger calculate(BigInteger a) {
        return BigInteger.ZERO.subtract(a);
    }
}
