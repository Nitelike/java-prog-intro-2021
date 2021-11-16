package markup;

import java.util.List;

public abstract class AbstractMarkdownElement extends AbstractBBCodeElement implements MarkdownElement {
    protected String leftMarkdownWrapper;
    protected String rightMarkdownWrapper;
    protected List<? extends MarkdownElement> inner;

    public AbstractMarkdownElement( String leftMarkdownWrapper, 
                                    String rightMarkdownWrapper, 
                                    String leftWrapper,
                                    String rightWrapper,
                                    List<? extends MarkdownElement> inner) {
        super(leftWrapper, rightWrapper, inner);
        this.leftMarkdownWrapper = leftMarkdownWrapper;
        this.rightMarkdownWrapper = rightMarkdownWrapper;
        this.inner = inner;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(leftMarkdownWrapper);
        for (MarkdownElement elem : inner) {
            elem.toMarkdown(sb);
        }
        sb.append(rightMarkdownWrapper);
    }
}