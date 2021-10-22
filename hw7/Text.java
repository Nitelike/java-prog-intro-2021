package markup;

public class Text implements Element {
	private String data;

	public Text(String data) {
		this.data = data;
	}

	@Override
	public String toMarkdown() {
		return data;
	}
}