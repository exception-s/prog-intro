package markup;

import java.util.List;

public class Paragraph extends AbstractMarkdown {
    public Paragraph(List<BBAble> marking) {
        super(marking);
    }

    @Override
    public void toBBCode(StringBuilder answer) {
        toBBCode(answer, "", "");
    }

    @Override
    public void toMarkdown(StringBuilder answer) {
        toMarkdown(answer, "");
    }
}
