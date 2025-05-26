package expression.exceptions;

import expression.*;
import expression.parser.TripleParser;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) {
        return new Parser(new StringSource(expression)).parseExpression();
    }

    private static class Parser extends BaseParser {
        protected Parser(CharSource source) {
            super(source);
        }

        public TripleExpression parseExpression() {
            final TripleExpression expr = parseAddSub();
            if (!eoe()) {
                throw error("Unexpected characters at the end: '" + remaining() + "'");
            }
            return expr;
        }

        private boolean testDigit() {
            return between('0', '9');
        }

        private String remaining() {
            StringBuilder sb = new StringBuilder();
            while (!eoe()) {
                sb.append(take());
            }
            return sb.toString();
        }

        private GeneralExpression parseAddSub() {
            GeneralExpression left = parseMulDiv();
            while (true) {
                skipWhitespace();
                if (take('+')) {
                    left = new CheckedAdd(left, parseMulDiv());
                } else if (take('-')) {
                    left = new CheckedSubtract(left, parseMulDiv());
                } else {
                    return left;
                }
            }
        }

        private GeneralExpression parseMulDiv() {
            GeneralExpression left = parseFactor();
            while (true) {
                skipWhitespace();
                if (take('*')) {
                    left = new CheckedMultiply(left, parseFactor());
                } else if (take('/')) {
                    left = new CheckedDivide(left, parseFactor());
                } else {
                    return left;
                }
            }
        }

        private GeneralExpression parseFunction() {
            if (take('l')) {
                expect('0');
                return new CheckedLeadingZeroes(parseFactor());
            } else if (take('t')) {
                expect('0');
                return new CheckedTrailingZeroes(parseFactor());
            }
            throw error("Unknown function");
        }

        private GeneralExpression parseFactor() {
            skipWhitespace();
            if (take('-')) {
                if (testDigit()) {
                    return new Const(parseNumber(true));
                } else {
                    return new CheckedNegate(parseFactor());
                }
            } else if (take('(')) {
                GeneralExpression expr = parseAddSub();
                expect(')');
                return expr;
            } else if (testDigit()) {
                return new Const(parseNumber(false));
            } else if (test('l') || test('t')) {
                return parseFunction();
            } else {
                return parseVariable();
            }
        }

        private int parseNumber(boolean isNegative) {
            final StringBuilder sb = new StringBuilder();
            if (isNegative) {
                sb.append('-');
            }
            if (take('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                takeDigits(sb);
            } else {
                throw error("Ожидалась цифра");
            }
            return Integer.parseInt(sb.toString());
        }

        private void takeDigits(StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }

        private GeneralExpression parseVariable() {
            char var = take();
            if (var != 'x' && var != 'y' && var != 'z') {
                throw error("Unknown variable: " + var);
            }
            return new Variable(String.valueOf(var));
        }
    }
}