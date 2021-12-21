package expression.parser;

import expression.*;

// Грамматика
// E  := P3 min E | P3 max E | P3
// P3 := P2 + P3 | P2 - P3 | P2
// P2 := P1 * P2 | P1 / P2 | P1
// P1 := -P0 | l0 P0 | t0 P0 | P0
// P0 := Const | Var | (E)

public class ExpressionParser implements Parser {
    @Override
    public TripleExpression parse(String expression) {
        return new TripleExpressionParser(expression).parse();
    }

    private static class TripleExpressionParser extends BaseParser {
        private final CharChecker numberChecker = new NumberChecker();
        private final CharChecker whitespaceChecker = new WhitespaceChecker();

        public TripleExpressionParser(String source) {
            super(new StringCharSource(source));
        }

        public TripleExpression parse() {
            TripleExpression result = parseE();
            skipWhitespaces();
            if (!end()) {
                throw error("Expected end of input");
            }
            return result;
        }

        private void skipWhitespaces() {
            while (test(whitespaceChecker)) {
                take();
            }
        }

        public AbstractExpression parseE() {
            skipWhitespaces();
            AbstractExpression p3 = parseP3();

            while (!end()) {
                skipWhitespaces();

                if (take('m')) {
                    if (take('i')) {
                        expect('n');
                        p3 = new Min(p3, parseP3());
                    } else if (take('a')) {
                        expect('x');
                        p3 = new Max(p3, parseP3());
                    }
                } else {
                    break;
                }
            }

            return p3;
        }

        private AbstractExpression parseP3() {
            skipWhitespaces();
            AbstractExpression p2 = parseP2();

            while (!end()) {
                skipWhitespaces();

                if (take('+')) {
                    p2 = new Add(p2, parseP2());
                } else if (take('-')) {
                    p2 = new Subtract(p2, parseP2());
                } else {
                    break;
                }
            }

            return p2;
        }

        private AbstractExpression parseP2() {
            skipWhitespaces();
            AbstractExpression p1 = parseP1();

            while (!end()) {
                skipWhitespaces();

                if (take('*')) {
                    p1 = new Multiply(p1, parseP1());
                } else if (take('/')) {
                    p1 = new Divide(p1, parseP1());
                } else {
                    break;
                }
            }

            return p1;
        }

        private AbstractExpression parseP1() {
            skipWhitespaces();
            if (take('-')) {
                if (test(numberChecker)) {
                    int val = parseP0().evaluate(0);
                    return new Const(val * (val < 0 ? 1 : -1));
                } else {
                    return new Invert(parseP1());
                }
            } else if (take('t')) {
                expect('0');
                return new TZeroes(parseP1());
            } else if (take('l')) {
                expect('0');
                return new LZeroes(parseP1());
            }

            return parseP0();
        }

        private AbstractExpression parseP0() {
            skipWhitespaces();

            if (take('(')) {
                AbstractExpression pe = parseE();
                skipWhitespaces();
                expect(')');
                return pe;
            }

            if (test('x') || test('y') || test('z')) {
                char var = take();
                return new Variable(Character.toString(var));
            }

            StringBuilder number = new StringBuilder();

            while(test(numberChecker)) {
                number.append(take());
            }

            if (number.length() > 0) {
                return new Const(Integer.parseUnsignedInt(number.toString()));
            }

            throw error("Expected opening bracket, constant or variable");
        }
    }

    private static class WhitespaceChecker implements CharChecker {
        @Override
        public boolean check(char ch) {
            return Character.isWhitespace(ch);
        }
    }

    private static class NumberChecker implements CharChecker {
        @Override
        public boolean check(char ch) {
            return Character.isDigit(ch);
        }
    }
}
