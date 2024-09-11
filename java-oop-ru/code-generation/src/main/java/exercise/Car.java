package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@AllArgsConstructor
@Value
@Getter
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    @SneakyThrows
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    @SneakyThrows
    public static Car deserialize(String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, Car.class);
    }
    // END
}
