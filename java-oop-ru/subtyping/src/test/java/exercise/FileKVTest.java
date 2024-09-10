package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    public void FileKVTesting() {
        Map<String, String> map1 = new HashMap<>();
        FileKV file = new FileKV(filepath.toString(), map1);
        assertEquals("default", file.get("key1", "default"));
        file.set("key3", "30");
        assertEquals("30", file.get("key3", "default"));
        map1.put("key1", "10");
        map1.put("key2", "20");
        file = new FileKV(filepath.toString(), map1);
        assertEquals(map1, file.toMap());
        assertEquals("10", file.get("key1", "default"));
        file.unset("key1");
        assertEquals("default", file.get("key1", "default"));
    }
    // END
}
