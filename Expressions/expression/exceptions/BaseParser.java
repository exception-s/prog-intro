package expression.exceptions;

public class BaseParser {
    private static final char END = '\0';
    private final CharSource source;
    private char ch = 0xffff;

    protected BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found ");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean eoe() {
        return take(END);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message, ch);
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}