package exercise;

import java.util.ArrayList;
import java.util.List;
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
