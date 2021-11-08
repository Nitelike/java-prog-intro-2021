package markup;

import java.util.List;

public abstract class AbstractList implements InList {
    private String bbCodeListOpenTag;
	private List<ListItem> inner;

    public AbstractList(String bbCodeListOpenTag, List<ListItem> inner) {
        this.bbCodeListOpenTag = bbCodeListOpenTag;
        this.inner = inner;
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append("[" + bbCodeListOpenTag + "]");
        for (ListItem elem : inner) {
            elem.toBBCode(sb);
        }
        sb.append("[/list]");
    }
}