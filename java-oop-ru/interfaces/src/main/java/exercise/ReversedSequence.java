package exercise;

// BEGIN
public class ReversedSequence implements  CharSequence {

    String str = "";

    public ReversedSequence(String text) {
        StringBuilder sb = new StringBuilder(text);
        this.str = sb.reverse().toString();
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public char charAt(int index) {
        return str.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return str.subSequence(start, end);
    }
}
// END
