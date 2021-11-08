package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> inner) {
        super("list=1", inner);
    }
}