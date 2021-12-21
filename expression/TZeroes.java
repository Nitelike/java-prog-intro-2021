package expression;

import java.math.BigInteger;

public class TZeroes extends AbstractUnaryOperation {
    public TZeroes(AbstractExpression operand) {
        super("t0", operand);
    }

    @Override
    public int calculate(int a) {
        return Integer.numberOfTrailingZeros(a);
    }

    @Override
    public BigInteger calculate(BigInteger a) {
        return null;
    }
}
