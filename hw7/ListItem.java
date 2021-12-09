package markup;

import java.util.List;

public class ListItem extends AbstractBBCodeElement {
    public ListItem(List<InList> inner) {
        super("[*]", "", inner);
    }
}