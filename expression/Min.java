package expression;

import java.math.BigInteger;

public class Min extends AbstractBinaryOperation {
    public Min(AbstractExpression operand1, AbstractExpression operand2) {
        super("min", operand1, operand2);
    }

    @Override
    public int calculate(int a, int b) {
        return Math.min(a, b);
    }

    @Override
    public BigInteger calculate(BigInteger a, BigInteger b) {
        return  a.min(b);
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
