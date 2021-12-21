package markup;

import java.util.List;

public class Strong extends AbstractMarkdownElement implements InParagraph {
    public Strong(List<InParagraph> inner) {
        super("__", "__", "[b]", "[/b]", inner);
    }
}