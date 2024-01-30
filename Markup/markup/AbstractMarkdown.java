package markup;

import java.util.List;

public abstract class AbstractMarkdown implements BBAble {
    private final List<BBAble> marking;
    protected AbstractMarkdown(List<BBAble> marking) {
        this.marking = marking;
    }
    public void toMarkdown(StringBuilder answer, String delimiter) {
        answer.append(delimiter);
        for (BBAble symbol : marking) {
            symbol.toMarkdown(answer);
        }
        answer.append(delimiter);
    }
    public void toBBCode(StringBuilder answer, String leftDelimiter, String rightDelimiter) {
        answer.append(leftDelimiter);
        for (BBAble symbol : marking) {
            symbol.toBBCode(answer);
        }
        answer.append(rightDelimiter);
    }
}
