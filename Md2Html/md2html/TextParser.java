package md2html;

import java.util.HashMap;

public class TextParser {
    private final StringBuilder text;
    private final HashMap<String, String> spec = new HashMap<>();
    private int index = 0;
    {
        spec.put("*", "em");
        spec.put("**", "strong");
        spec.put("_", "em");
        spec.put("__", "strong");
        spec.put("--", "s");
        spec.put("`", "code");
        spec.put("%", "var");
    }
    TextParser(StringBuilder text) {
        this.text = text;
    }
    private StringBuilder highlight(String special) {
        StringBuilder sb = new StringBuilder();
        boolean isUnusual;
        sb.append("<").append(spec.get(special)).append(">");
        index++;
        while (index < text.length()) {
            char c = text.charAt(index);
            if (special.charAt(0) == c) {
                sb.append("</").append(spec.get(special)).append(">");
                return sb;
            }

        }
        return new StringBuilder();
    }
    public void toHtml(StringBuilder result) {
        int length = text.length();
        while (index < length) {
            char c = text.charAt(index);
            switch (c) {
                case '*' -> {
                    if (index + 1 < length && text.charAt(index + 1) == '*') {
                        index++;
                        highlight( "**");
                    } else {
                        highlight("*");
                    }
                }
                case '_' -> {
                    if (index + 1 < length && text.charAt(index + 1) == '_') {
                        index++;
                        highlight("__");
                    } else {
                        highlight("_");
                    }
                }
                case '-' -> {
                    if (index + 1 < length && text.charAt(index + 1) == '-') {
                        index++;
                        highlight("--");
                    } else {
                        result.append(c);
                    }
                }
                case '`' -> {
                    highlight("`");
                }
                case '%' -> {
                    highlight("%");
                }
                case '\\' -> {
                    index++;
                    if (index < length) {
                        result.append(text.charAt(index));
                    }
                }
                default -> {
                    result.append(c);
                    index++;
                }
            }
        }
    }
}
