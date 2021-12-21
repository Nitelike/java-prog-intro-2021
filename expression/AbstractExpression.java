package expression;

public interface AbstractExpression extends Expression, TripleExpression, BigIntegerExpression {
    int getPriority();
    boolean needAdditionalBrackets();
}
