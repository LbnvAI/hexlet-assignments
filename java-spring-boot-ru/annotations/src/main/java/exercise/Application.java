package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (Method method : address.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                String methodName = method.getName();
                String methodReturnType = method.getReturnType().getName();
                if (methodReturnType.contains(".")) {
                    methodReturnType = methodReturnType.substring(10);
                }
                System.out.println("Method " + methodName + " returns a value of type " + methodReturnType);
            }
        }
        // END
    }
}
