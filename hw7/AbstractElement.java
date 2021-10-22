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
	public String toMarkdown() {
		StringBuilder sb = new StringBuilder();
		sb.append(wrapper);
		for (Element elem : inner) {
			sb.append(elem.toMarkdown());
		}
		sb.append(wrapper);
		return sb.toString();
	}
}