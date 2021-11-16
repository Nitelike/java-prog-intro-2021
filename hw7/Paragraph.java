package markup;

import java.util.List;

public class Paragraph extends AbstractMarkdownElement implements InList {
    public Paragraph(List<InParagraph> inner) {
        super("", "", "", "", inner);
    }
}