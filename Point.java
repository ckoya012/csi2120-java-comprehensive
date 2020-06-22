public class Point {

    private int x;
    private int y;
    private int width;
    private int height;

    public Point (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double calculateXCoordinate() {
        return x + width/2;
    }
    
    public double calculateYCoordinate() {
        return y + height/2;
    }
}