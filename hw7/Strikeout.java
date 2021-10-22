package markup;

import java.util.List;

public class Strikeout extends AbstractElement {
	public Strikeout(List<Element> inner) {
		super("~", inner);
	}
}