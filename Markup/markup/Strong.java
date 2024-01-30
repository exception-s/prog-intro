package markup;

import java.util.List;

public class Strong extends AbstractMarkdown {
    public Strong(List<BBAble> marking) {
        super(marking);
    }
    @Override
    public void toBBCode(StringBuilder answer) {
        toBBCode(answer, "[b]", "[/b]");
    }

    @Override
    public void toMarkdown(StringBuilder answer) {
        toMarkdown(answer, "__");
    }
}
