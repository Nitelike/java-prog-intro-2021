package expression;

import java.math.BigInteger;

public class Add extends AbstractBinaryOperation {
    public Add(AbstractExpression operand1, AbstractExpression operand2) {
        super("+", operand1, operand2);
    }

    @Override
    public int calculate(int a, int b) {
        return a + b;
    }

    @Override
    public BigInteger calculate(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean needAdditionalBrackets() {
        return false;
    }
}
