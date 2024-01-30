package markup;

public class Text implements BBAble {
    private final String text;
    public Text(String text) {
        this.text = text;
    }
    @Override
    public void toBBCode(StringBuilder answer) {
        answer.append(text);
    }
    @Override
    public void toMarkdown(StringBuilder answer) {
        answer.append(text);
    }
}
