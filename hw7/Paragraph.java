package markup;

import java.util.List;

public class Paragraph implements MarkdownElement, InList {
	private List<InParagraph> inner;

    public Paragraph(List<InParagraph> inner) {
        this.inner = inner;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (InParagraph elem : inner) {
            elem.toMarkdown(sb);
        }
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        for (InParagraph elem : inner) {
            elem.toBBCode(sb);
        }
    }
}