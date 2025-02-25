abstract public class Figures {
    //sets the attributes all figures will have
    private double area;
    private double perimeter;

    //constructor
    public Figures() {
        this.area = 0;
        this.perimeter = 0;
    }

    //getters and setters
    public double getArea() {
        return area;
    }

    public void setArea(double area) {this.area = area;}

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    //abstract methods
    public abstract void CalculateArea();

    public abstract void CalculatePerimeter();
}
