package markup;

import java.util.List;

public class Paragraph extends AbstractElement {
	public Paragraph(List<Element> inner) {
		super("", inner);
	}

	public void toMarkdown(StringBuilder sb) {
		sb.append(super.wrapper);
		for (Element elem : super.inner) {
			sb.append(elem.toMarkdown());
		}
		sb.append(super.wrapper);
	}	
}