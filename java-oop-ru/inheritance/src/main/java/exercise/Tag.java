package exercise;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Tag {
    protected String name;
    protected Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = new LinkedHashMap<>(attributes);
    }

    protected String getAttributesString() {
        List<String> result = new ArrayList<>();
        result.add("<" + name);
        attributes.forEach((k, v) -> {
            result.add(k + "=\"" + v + "\"");
        });
        return String.join(" ", result) + ">";
    }
}
// END
