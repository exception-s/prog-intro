package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WordsScanner implements AutoCloseable {
    private final Reader READER;
    public final char[] BUFFER = new char[1024];
    private final StringBuilder DATA = new StringBuilder();
    public final StringBuilder S = new StringBuilder();
    private final String LINE_SEP = System.lineSeparator();
    private int pointer;
    private int read;
    private int sepID = 0;

    public WordsScanner(InputStream source) {
        READER = new InputStreamReader(source, StandardCharsets.UTF_8);
    }
    public WordsScanner(Reader reader) {
        READER = reader;
    }
    public WordsScanner(File file) throws FileNotFoundException {
        READER = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
    }
    public WordsScanner(String string) {
        READER = new StringReader(string);
    }
    public void close() throws IOException {
        READER.close();
    }
    void reset() throws IOException {
        pointer = 0;
        read = READER.read(BUFFER);
    }
    boolean isSeparator() throws IOException {
        while (sepID != LINE_SEP.length()) {
            if (BUFFER[pointer] == LINE_SEP.charAt(sepID)) {
                S.append(BUFFER[pointer]);
                sepID++;
                pointer++;
                if (pointer == read) reset();
            }
            else {
                this.pointer -= sepID - 1;
                DATA.append(S);
                return false;
            }
        }
        return true;
    }
    boolean haveInput() throws IOException {
        if (read < 0) {
            reset();
            return read > 0;
        }
        return true;
    }
    public boolean hasNextLine() throws IOException {
        if (BUFFER[0] == (char) 0) reset();
        while (read > 0) {
            while (pointer < read) {
                if (BUFFER[pointer] == LINE_SEP.charAt(sepID)) {
                    if (isSeparator()) {
                        sepID = 0;
                        S.setLength(0);
                        return true;
                    }
                }
                else {
                    DATA.append(BUFFER[pointer]);
                    pointer++;
                }
            }
            reset();
        }
        S.setLength(0);
        return !DATA.isEmpty();
    }
    public String nextLine() {
        String s = DATA.toString();
        DATA.setLength(0);
        return s;
    }
    void skipWhitespaces() throws IOException{
        while (haveInput() && (!Character.isLetter(BUFFER[pointer]) &&
                !(Character.getType(BUFFER[pointer]) == Character.DASH_PUNCTUATION)
                && !(BUFFER[pointer] == 39)) && BUFFER[pointer] != LINE_SEP.charAt(0)) {
            pointer++;
            if (pointer >= read) {
                reset();
            }
        }
    }
    public boolean hasNext() throws IOException{
        if (BUFFER[0] == (char) 0) reset();
        skipWhitespaces();
        while (read > 0) {
            while (pointer < read && (Character.isLetter(BUFFER[pointer]) ||
                    (Character.getType(BUFFER[pointer]) == Character.DASH_PUNCTUATION)
                    || (BUFFER[pointer] == 39))) {
                S.append(BUFFER[pointer]);
                pointer++;
            }
            if (pointer >= read || (BUFFER[pointer] == LINE_SEP.charAt(0) && isSeparator())) {
                reset();
            }
            else {
                break;
            }
        }
        return !S.isEmpty();
    }
    public String next() {
        String str = S.toString();
        S.setLength(0);
        return str;
    }
    public int nextInt() {
        return Integer.parseInt(next());
    }
}
