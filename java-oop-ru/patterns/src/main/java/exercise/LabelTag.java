package exercise;

// BEGIN
public class LabelTag implements TagInterface {

    private String value;
    TagInterface tag;

    public LabelTag(String value, TagInterface tag) {
        this.value = value;
        this.tag = tag;
    }

    @Override
    public String render() {
        return "<label>" + value + tag.render() + "</label>";
    }
}
// END
