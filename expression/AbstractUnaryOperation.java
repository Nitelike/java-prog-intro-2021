package expression;

import java.math.BigInteger;

public abstract class AbstractUnaryOperation implements AbstractExpression {
    public final String operation;
    public final AbstractExpression operand;

    public AbstractUnaryOperation(String operation, AbstractExpression operand) {
        this.operation = operation;
        this.operand = operand;
    }

    abstract public int calculate(int a);

    abstract public BigInteger calculate(BigInteger a);

    @Override
    public int evaluate(int x) {
        return calculate(x);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return calculate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(operand.evaluate(x, y, z));
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
        return operation + "(" + operand.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (operand.getPriority() > getPriority()) {
            return operation + "(" + operand.toMiniString() + ")";
        } else {
            return operation + " " + operand.toMiniString();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        AbstractUnaryOperation expression = (AbstractUnaryOperation) obj;
        return operand.equals(expression.operand);
    }

    @Override
    public int hashCode() {
        int prime = 809;
        int hash = 1;
        hash = hash * prime + operand.hashCode();
        hash = hash * prime + operation.hashCode();
        return hash;
    }
}
