package markup;

import java.util.List;

public class Emphasis extends AbstractMarkdown {
    public Emphasis(List<BBAble> marking) {
        super(marking);
    }
    @Override
    public void toBBCode(StringBuilder answer) {
        toBBCode(answer, "[i]", "[/i]");
    }

    @Override
    public void toMarkdown(StringBuilder answer) {
        toMarkdown(answer, "*");
    }
}
