package exercise;

// BEGIN
public class Circle {
    Point center;
    int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public int getSquare() throws NegativeRadiusException {
        if (radius < 0) throw new NegativeRadiusException("");
        else return (int) Math.round(Math.PI * Math.pow(radius, 2));
    }
}
// END
