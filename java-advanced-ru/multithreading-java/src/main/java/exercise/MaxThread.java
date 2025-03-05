package exercise;

// BEGIN
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class MaxThread extends Thread {

    private int[] numbers;
    private int result;

    @Override
    public void run() {
        result = Arrays.stream(numbers).max().getAsInt();
    }
}
// END
