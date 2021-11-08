package markup;

import java.util.List;

public abstract class AbstractElement implements InParagraph {
    protected String markdownWrapper;
    protected String bbCodeWrapper;
    protected List<InParagraph> inner;

    public AbstractElement(String markdownWrapper, String bbCodeWrapper, List<InParagraph> inner) {
        this.markdownWrapper = markdownWrapper;
        this.bbCodeWrapper = bbCodeWrapper;
        this.inner = inner;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(markdownWrapper);
        for (InParagraph elem : inner) {
            elem.toMarkdown(sb);
        }
        sb.append(markdownWrapper);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append("[" + bbCodeWrapper + "]");
        for (InParagraph elem : inner) {
            elem.toBBCode(sb);
        }
        sb.append("[/" + bbCodeWrapper + "]");
    }
}