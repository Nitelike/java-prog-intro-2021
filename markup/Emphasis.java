package markup;

import java.util.List;

public class Emphasis extends AbstractMarkdownElement implements InParagraph {
    public Emphasis(List<InParagraph> inner) {
        super("*", "*", "[i]", "[/i]", inner);
    }
}