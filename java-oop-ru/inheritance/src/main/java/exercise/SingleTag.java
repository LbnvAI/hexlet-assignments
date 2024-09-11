package exercise;

import java.util.Map;

// BEGIN
public final class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    public String toString() {
        return getAttributesString();
    }
}
// END
