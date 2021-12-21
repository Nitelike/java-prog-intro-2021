package expression;

import java.math.BigInteger;

public class Divide extends AbstractBinaryOperation {
    public Divide(AbstractExpression operand1, AbstractExpression operand2) {
        super("/", operand1, operand2);
    }

    @Override
    public int calculate(int a, int b) {
        return a / b;
    }

    @Override
    public BigInteger calculate(BigInteger a, BigInteger b) {
        return a.divide(b);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean needAdditionalBrackets() {
        return true;
    }
}
