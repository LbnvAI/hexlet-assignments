package exercise;

// BEGIN
public class Segment {
    Point begin;
    Point end;

    public Segment(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Point getBeginPoint() {
        return begin;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getMidPoint() {
        int xMid = (begin.getX() + end.getX()) / 2;
        int yMid = (begin.getY() + end.getY()) / 2;
        return new Point(xMid, yMid);
    }
}
// END
