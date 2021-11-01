package markup;

import java.util.List;

public abstract class AbstractElement implements Element {
    protected String wrapper;
    protected List<Element> inner;

    public AbstractElement(String wrapper, List<Element> inner) {
        this.wrapper = wrapper;
        this.inner = inner;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(wrapper);
        for (Element elem : inner) {
            elem.toMarkdown(sb);
        }
        sb.append(wrapper);
    }
}