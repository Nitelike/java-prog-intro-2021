package md2html;

import java.util.Map;
import java.util.HashMap;

public class Md2HtmlParser {
    private String block;
    private int index;
    private final String[] markdownTags = {"**", "*", "__", "_", "```", "~", "`", "--"};
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
        for (index = 0; index < block.length(); index++) {
            if (Character.isWhitespace(block.charAt(index))) {
                return index++;
            } else if (block.charAt(index) != '#') {
                break;
            }
        }
        return 0;
    }

    private String escapeIfTag(char c) {
        String cur = convertIfSpecial(c);
        if (cur.equals("*") || cur.equals("_") || cur.equals("`")) {
            return cur;
        } else {
            return "\\" + cur;
        }
    }

    private String getOpenTag() {
        for (String tag : markdownTags) {
            if (substr(index, tag.length()).equals(tag)) {
                return tag;
            }
        }
        return null;
    }

    private String convertIfSpecial(char c) {
        return specialChars.containsKey(c) ? specialChars.get(c) : Character.toString(c);
    }

    private String convertCloseTag(String closeTag) {
        return closeTags.get(closeTag);
    }

    private String substr(int l, int len) {
        if (l + len <= block.length()) {
            return block.substring(l, l + len);
        }
        return "";
    }

    private String convertFromMarkdown() {
        StringBuilder res = new StringBuilder();
        String openTag = getOpenTag();

        index += openTag.length();
        int start = index;

        String converted = parseInline(openTag);
        String expectedCloseTag = convertCloseTag(openTag);

        if (openTag.length() > 1 || converted.endsWith(expectedCloseTag)) {
            res.append(expectedCloseTag.substring(0, 1) + expectedCloseTag.substring(2));
        } else {
            res.append(openTag);
        }        

        if (converted.endsWith(expectedCloseTag) && openTag.equals("```")) {
            res.append(block.substring(start, index - 3));
            res.append(expectedCloseTag);
        } else if (openTag.equals("```")) {
            res.append(block.substring(start));
        } else {
            res.append(converted);
        }
        
        index--;

        return res.toString();
    }


    private String parseInline(String closeTag) {
        StringBuilder res = new StringBuilder();

        while (index < block.length()) {
            String openTag = getOpenTag();
            char cur = block.charAt(index);

            if (index > 0 && block.charAt(index - 1) == '\\') {
                res.append(escapeIfTag(cur));
            } else if (openTag != null && closeTag != null && openTag.equals(closeTag)) {
                res.append(convertCloseTag(closeTag));
                index += closeTag.length();
                return res.toString();
            } else if (openTag != null && (closeTag == null || !closeTag.equals("```"))) {
                res.append(convertFromMarkdown());
            } else if (cur != '\\') {
                res.append(convertIfSpecial(cur));
            }

            index++;
        }

        return res.toString();
    }

    public String parseBlock(StringBuilder sb) {
        block = sb.toString();
        index = 0;

        int headerLvl = countHeaderTags();

        if (headerLvl == 0) {
            return "<p>" + substr(0, index) + parseInline(null) + "</p>";
        } else {
            return "<h" + Integer.toString(headerLvl) + ">" + parseInline(null) + "</h" + Integer.toString(headerLvl) + ">";
        }
    }
}