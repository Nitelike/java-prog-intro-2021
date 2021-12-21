package markup;

import java.util.List;

public abstract class AbstractList extends AbstractBBCodeElement implements InList {
    public AbstractList(String bbCodeListOpenTag, List<ListItem> inner) {
        super(bbCodeListOpenTag, "[/list]", inner);
    }
}