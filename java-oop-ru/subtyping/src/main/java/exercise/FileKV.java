package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// BEGIN
public class FileKV implements KeyValueStorage {

    String path;

    public FileKV(String path, Map<String, String> map) {
        this.path = path;
        Utils.writeFile(this.path, Utils.serialize(map));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> map = Utils.deserialize(Utils.readFile(path));
        map.put(key, value);
        Utils.writeFile(path, Utils.serialize(map));
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = Utils.deserialize(Utils.readFile(path));
        map.remove(key);
        Utils.writeFile(path, Utils.serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        String result = Utils.deserialize(Utils.readFile(path)).get(key);
        return Objects.isNull(result) ? defaultValue : result;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = Utils.deserialize(Utils.readFile(path));
        return new HashMap<>(map);
    }
}
// END
