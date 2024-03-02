package md2html;

import markup.Paragraph;

public class ParagraphParser {
    private final StringBuilder text;
    ParagraphParser(StringBuilder text) {
        this.text = text;
    }
    public void toHtml(StringBuilder result) {
        result.append("<p>");
        new TextParser(text).toHtml(result);
        result.append("</p>");
    }
}
