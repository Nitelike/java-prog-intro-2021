package markup;

public interface MarkdownElement extends BBCodeElement {
    void toMarkdown(StringBuilder sb);
}