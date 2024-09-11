package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

// BEGIN
public final class PairedTag extends Tag {

    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = new ArrayList<>(children);
    }

    public String toString() {
        List<String> result = new ArrayList<>();
        result.add(getAttributesString());
        children.forEach(tag -> result.add(tag.toString()));
        result.add(body);
        return String.join("", result) + "</" + name + ">";
    }
}
// END
