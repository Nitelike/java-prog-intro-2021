package markup;

import java.util.List;

public class ListItem implements BBCodeElement {
	private List<InList> inner;

    public ListItem(List<InList> inner) {
        this.inner = inner;
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append("[*]");
        for (InList elem : inner) {
            elem.toBBCode(sb);
        }
    }
}