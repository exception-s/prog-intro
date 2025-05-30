package expression.exceptions;

public class StringSource implements CharSource {
    private final String data;
    private int pos;
    public StringSource(final String data) {
        this.data = data;
    }
    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public IllegalArgumentException error(final String message, final char c) {
        return new IllegalArgumentException(message);
    }
}
