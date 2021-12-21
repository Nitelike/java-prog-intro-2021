package markup;

import java.util.List;

public class Strikeout extends AbstractMarkdownElement implements InParagraph {
    public Strikeout(List<InParagraph> inner) {
        super("~", "~", "[s]", "[/s]", inner);
    }
}