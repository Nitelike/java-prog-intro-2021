package markup;

import java.util.List;

public class Strong extends AbstractElement {
    public Strong(List<InParagraph> inner) {
        super("__", "b", inner);
    }
}