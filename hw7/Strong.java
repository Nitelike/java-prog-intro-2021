package markup;

import java.util.List;

public class Strong extends AbstractElement {
    public Strong(List<Element> inner) {
        super("__", inner);
    }
}