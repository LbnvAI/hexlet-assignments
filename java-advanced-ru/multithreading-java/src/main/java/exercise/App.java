package exercise;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws InterruptedException {
        int[] numbers = {10, -4, 67, 100, -100, 8};
        System.out.println(getMinMax(numbers));
    }

    public static Map<String, Integer> getMinMax(int[] numbers) throws InterruptedException {
        var t1 = new MinThread();
        t1.setNumbers(numbers);
        var t2 = new MaxThread();
        t2.setNumbers(numbers);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        var result = new HashMap<String, Integer>();
        result.put("min", t1.getResult());
        result.put("max", t2.getResult());
        return result;
    }
}
