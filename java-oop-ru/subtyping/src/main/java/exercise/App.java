package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> swapped = new HashMap<>();
        storage.toMap().forEach((k, v) -> {
            swapped.put(v, k);
            storage.unset(k);
        });
        swapped.forEach(storage::set);
    }
}
// END
