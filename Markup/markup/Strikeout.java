package markup;

import java.util.List;

public class Strikeout extends AbstractMarkdown {
    public Strikeout(List<BBAble> marking) {
        super(marking);
    }
    @Override
    public void toBBCode(StringBuilder answer) {
        toBBCode(answer, "[s]", "[/s]");
    }

    @Override
    public void toMarkdown(StringBuilder answer) {
        toMarkdown(answer, "~");
    }
}
