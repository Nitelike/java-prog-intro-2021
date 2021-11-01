package markup;

import java.util.List;

public class Emphasis extends AbstractElement {
    public Emphasis(List<Element> inner) {
        super("*", inner);
    }
}