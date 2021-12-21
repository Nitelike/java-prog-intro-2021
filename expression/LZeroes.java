package expression;

import java.math.BigInteger;

public class LZeroes extends AbstractUnaryOperation {
    public LZeroes(AbstractExpression operand) {
        super("l0", operand);
    }

    @Override
    public int calculate(int a) {
        return Integer.numberOfLeadingZeros(a);
    }

    @Override
    public BigInteger calculate(BigInteger a) {
        return null;
    }
}
