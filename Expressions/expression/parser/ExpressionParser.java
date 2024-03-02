package expression.parser;

import expression.*;


public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) {
        //System.err.println(expression);
        return new Parser(new StringSource(expression)).parseExpression();
    }

    private static class Parser extends BaseParser {
        protected Parser(CharSource source) {
            super(source);
        }
        public TripleExpression parseExpression() {
            final TripleExpression result = expression();
            if (eoe()) {
                return result;
            }
            throw error("End of expression expected");
        }


        private GeneralExpression expression() {
            GeneralExpression first = term();
            skipWhitespace();
            while (!eoe() && !test(')')) {
                char operation = take();
                final GeneralExpression second = term();
                switch (operation) {
                    case '+' -> {
                        first = new Add(first, second);
                    }
                    case '-' -> {
                        first = new Subtract(first, second);
                    }
                    default -> throw error("Unexpected operation");
                }
            }
            skipWhitespace();
            return first;
        }


        private GeneralExpression term() {
            skipWhitespace();
            GeneralExpression first = factor();
            skipWhitespace();
            if (take('(')) {
                final GeneralExpression result = expression();
                expect(')');
                take();
                return result;
            } else {
                while (!eoe() && !test(')')) {
                    skipWhitespace();
                    if (test('+') || test('-')) {
                        return first;
                    }
                    char operation = take();
                    skipWhitespace();
                    final GeneralExpression second = factor();
                    skipWhitespace();
                    switch (operation) {
                        case '*' -> {
                            first = new Multiply(first, second);
                        }
                        case '/' -> {
                            first = new Divide(first, second);
                        }
                        default -> throw error("Unexpected operation");
                    }
                }
                return first;
            }
        }


        private GeneralExpression factor() {
            skipWhitespace();
            if (take('-')) {
                return negate();
            } else {
                if (take('(')) {
                    final GeneralExpression result = expression();
                    expect(')');
                    return result;
                } else {
                    skipWhitespace();
                    if (take('x')) {
                        return new Variable("x");
                    } else if (take('y')) {
                        return new Variable("y");
                    } else if (take('z')) {
                        return new Variable("z");
                    } else {
                        return new Const(parseConst(false));
                    }
                }
            }
        }

        private GeneralExpression negate() {
            skipWhitespace();
            if (take('(')) {
                final GeneralExpression result = expression();
                expect(')');
                return new UnaryMinus(result);
            } else if (take('-')) {
                final GeneralExpression result = negate();
                return new UnaryMinus(result);
            } else {
                skipWhitespace();
                if (take('x')) {
                    return new UnaryMinus(new Variable("x"));
                } else if (take('y')) {
                    return new UnaryMinus(new Variable("y"));
                } else if (take('z')) {
                    return new UnaryMinus(new Variable("z"));
                } else {
                    return new Const(parseConst(true));
                }
            }
        }


        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }


        private int parseConst(boolean negate) {
            skipWhitespace();
            final StringBuilder sb;
            if (negate) {
                sb = new StringBuilder("-");
            } else {
                sb = new StringBuilder();
            }
            takeConst(sb);
            return Integer.parseInt(sb.toString());
        }

        private void takeConst(final StringBuilder sb) {
            if (take('-')) {
                sb.append('-');
            }
            if (take('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                takeDigits(sb);
            } else {
                throw error("Invalid number");
            }
        }
    }
}
