package exercise;

// BEGIN
public interface Home {
    double getArea();

    @Override
    String toString();

    default int compareTo(Home another) {
        return Double.compare(this.getArea(), another.getArea());
    }
}
// END
