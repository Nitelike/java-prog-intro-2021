package expression;

import java.math.BigInteger;

public abstract class AbstractBinaryOperation implements AbstractExpression {
    public final String operation;
    public final AbstractExpression operand1, operand2;

    public AbstractBinaryOperation(String operation, AbstractExpression operand1, AbstractExpression operand2) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    abstract public int calculate(int a, int b);

    abstract public BigInteger calculate(BigInteger a, BigInteger b);

    @Override
    public int evaluate(int x) {
        return calculate(operand1.evaluate(x), operand2.evaluate(x));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return calculate(operand1.evaluate(x), operand2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(operand1.evaluate(x, y, z), operand2.evaluate(x, y, z));
    }

    public String addBrackets(String s, boolean add) {
        if (add) {
            return "(" + s + ")";
        }
        return s;
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + " " + operation + " " + operand2.toString() + ")";
    }

    @Override
    public String toMiniString() {
        boolean needLeft = operand1.getPriority() > getPriority();
        int mask = (needAdditionalBrackets() ? 2 : 0) + (operand2.needAdditionalBrackets() ? 1 : 0);
        boolean needRight = operand2.getPriority() > getPriority() ||
                (operand2.getPriority() == getPriority() && (mask > 1 || (mask == 1 && getPriority() < 2)));
        return addBrackets(operand1.toMiniString(), needLeft)
                +  " " + operation + " " +
                addBrackets(operand2.toMiniString(), needRight);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        AbstractBinaryOperation expression = (AbstractBinaryOperation) obj;
        return operand1.equals(expression.operand1) && operand2.equals(expression.operand2);
    }

    @Override
    public int hashCode() {
        int prime = 809;
        int hash = 1;
        hash = hash * prime + operand1.hashCode();
        hash = hash * prime + operand2.hashCode();
        hash = hash * prime + operation.hashCode();
        return hash;
    }
}
