package md2html;

public class Partition {
    private final StringBuilder text;
    private boolean header = false;
    Partition(StringBuilder text) {
        this.text = text;
    }
    public void toHtml(StringBuilder result) {
        int index = 0;
        while (text.charAt(index) == '#' && index < text.length()) {
            index++;
        }
        if (index > 0 && Character.isWhitespace(text.charAt(index))) {
            header = true;
        }
        if (header) {
            new HeaderParser(text).toHtml(result);
        }
        else {
            new ParagraphParser(text).toHtml(result);
        }
    }
}
