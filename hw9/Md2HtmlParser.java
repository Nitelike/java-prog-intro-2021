package md2html;

import java.util.Map;
import java.util.HashMap;

public class Md2HtmlParser {
    private String block;
    private int index;
    private String[] markdownTags = {"**", "*", "__", "_", "```", "~", "`", "--"};
    private Map<Character, String> specialChars = new HashMap<>();
    private Map<String, String> closeTags = new HashMap<>();

    {
        specialChars.put('&', "&amp;");
        specialChars.put('>', "&gt;");
        specialChars.put('<', "&lt;");

        closeTags.put("**", "</strong>");
        closeTags.put("*", "</em>");
        closeTags.put("__", "</strong>");
        closeTags.put("_", "</em>");
        closeTags.put("--", "</s>");
        closeTags.put("```", "</pre>");
        closeTags.put("`", "</code>");
        closeTags.put("~", "</mark>");
    }

    private int countHeaderTags() {
        while (index < block.length() && block.charAt(index) == '#') {
            index++;
        }
        if (Character.isWhitespace(block.charAt(index))) {
            return index++;
        }
        return 0;
    }

    private String shieldingMarkdownSymbols() {
        char cur = block.charAt(index);
        if (cur == '*' || cur == '_' || cur == '`') {
            return Character.toString(cur);
        } else {
            return ("\\" + convertIfSpecial(cur));
        }
    }

    private String getOpenTag() {
        for (String tag : markdownTags) {
            //if (block.substring(index).startsWith(tag)) {
            //    return tag;
            //}
            if (index + tag.length() <= block.length() && block.substring(index, index + tag.length()).equals(tag)) {
                return tag;
            }
        }
        return null;
    }

    private String convertIfSpecial(char c) {
        return specialChars.containsKey(c) ? specialChars.get(c) : Character.toString(c);
    }

    private String convertFromMarkdown() {
        StringBuilder res = new StringBuilder();
        String openTag = getOpenTag();

        index += openTag.length();
        int start = index;
        boolean closingTagExists = false;

        String converted = parseInline(openTag);
        String expectedCloseTag = convertCloseTag(openTag);

        if (converted.endsWith(expectedCloseTag)) {
            res.append(expectedCloseTag.substring(0, 1) + expectedCloseTag.substring(2));
            closingTagExists = true;
        } else {
            res.append(openTag);
        }

        if (closingTagExists && openTag.equals("```")) {
            res.append(block.substring(start, index - 3));
            res.append(expectedCloseTag);
        } else {
            res.append(converted);
        }
        
        index--;

        return res.toString();
    }

    private String convertCloseTag(String closeTag) {
        return closeTags.get(closeTag);
    }

    private boolean checkForCloseTag(String closeTag) {
        if (closeTag == null || index + closeTag.length() > block.length()) {
            return false;
        }
        return block.substring(index, index + closeTag.length()).equals(closeTag);
    }

    private String parseInline(String closeTag) {
        StringBuilder res = new StringBuilder();

        while (index < block.length()) {
            String openTag = getOpenTag();

            if (index > 0 && block.charAt(index - 1) == '\\') {
                res.append(shieldingMarkdownSymbols());
            } else if (openTag != null && openTag != closeTag) {
                res.append(convertFromMarkdown());
            } else if (checkForCloseTag(closeTag)) {
                res.append(convertCloseTag(closeTag));
                index += closeTag.length();
                return res.toString();
            } else if (block.charAt(index) == '\\') {
                if (index + 1 >= block.length()) {
                    res.append(block.charAt(index));
                }
            } else if (openTag != null) {
                res.append(convertFromMarkdown());
            } else {
                res.append(convertIfSpecial(block.charAt(index)));
            }

            index++;
        }

        return res.toString();
    }

    public String parseBlock(StringBuilder sb) {
        block = sb.toString();
        index = 0;

        StringBuilder res = new StringBuilder();
        int headerLvl = countHeaderTags();

        if (headerLvl == 0) {
            res.append("<p>");
            res.append(block.substring(0, index));
            res.append(parseInline(null));
            res.append("</p>");
        } else {
            res.append("<h" + Integer.toString(headerLvl) + ">");
            res.append(parseInline(null));
            res.append("</h" + Integer.toString(headerLvl) + ">");
        }

        return res.toString();
    }
}