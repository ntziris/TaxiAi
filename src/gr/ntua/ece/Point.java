package gr.ntua.ece;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(String position) {
        // position in the format: "x y"
        String[] parts = position.split(" ");
        this.x = Double.valueOf(parts[0].trim());
        this.y = Double.valueOf(parts[1].trim());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void print() {
        System.out.println("(x, y) = (" + this.x + ", " + this.y + ")");
    }

    public double euclid(Point p) {
        return Math.sqrt(Math.pow(this.x - p.getX(), 2) + Math.pow(this.y - p.getY(), 2));
    }

    @Override
    public String toString() {
        return "(x, y) = (" + this.x + ", " + this.y + ")";
    }
}
