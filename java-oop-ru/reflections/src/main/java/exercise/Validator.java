package exercise;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.HashMap;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> invalid = new ArrayList<>();
        for (Field field : address.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(address);
                    if (Objects.isNull(value)) {
                        var temp = field.toString().split("\\.");
                        invalid.add(temp[temp.length - 1]);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return invalid;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> errors = new HashMap<>();
        for (Field field : address.getClass().getDeclaredFields()) {
            List<String> errList = new ArrayList<>();
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(address);
                    if (Objects.isNull(value)) {
                        errList.add("can not be null");
                    }
                } catch (RuntimeException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(MinLength.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(address);
                    if (Objects.equals(value.getClass(), String.class)) {
                        MinLength annotation = field.getAnnotation(MinLength.class);
                        if (value.toString().length() < annotation.minLength()) {
                            errList.add("length less than" + annotation.minLength());
                        }
                    }
                } catch (RuntimeException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (!errList.isEmpty()) {
                errors.put(field.getName(), errList);
            }
        }
        return errors;
    }
}
// END
