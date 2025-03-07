package exercise;

class SafetyList {
    // BEGIN
    private int[] storage = new int[2500];
    private int fillPos = -1;

    public synchronized void add(int num) {
        storage[fillPos + 1] = num;
        fillPos++;
    }

    public int get(int pos) {
        return storage[pos];
    }

    public int getSize() {
        return fillPos + 1;
    }
    // END
}
