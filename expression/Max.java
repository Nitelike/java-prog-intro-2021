package expression;

import java.math.BigInteger;

public class Max extends AbstractBinaryOperation {
    public Max(AbstractExpression operand1, AbstractExpression operand2) {
        super("max", operand1, operand2);
    }

    @Override
    public int calculate(int a, int b) {
        return Math.max(a, b);
    }

    @Override
    public BigInteger calculate(BigInteger a, BigInteger b) {
        return a.max(b);
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean needAdditionalBrackets() {
        return false;
    }
}
