package md2html;

public class HeaderParser {
    private final StringBuilder text;
    private int headerLevel = 0;
    HeaderParser(StringBuilder text) {
        this.text = text;
    }
    public void toHtml(StringBuilder result) {
        while (text.charAt(headerLevel) == '#' && headerLevel < text.length()) {
            headerLevel++;
        }
        result.append("<h").append(headerLevel).append(">");
        StringBuilder sb = new StringBuilder(text.substring(headerLevel + 1));
        new TextParser(sb).toHtml(result);
        result.append("</h").append(headerLevel).append(">");
    }
}
