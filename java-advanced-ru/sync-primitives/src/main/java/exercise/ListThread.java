package exercise;

import lombok.AllArgsConstructor;

// BEGIN
@AllArgsConstructor
public class ListThread implements Runnable {
    private SafetyList list;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.add(i);
        }
    }
}
// END
