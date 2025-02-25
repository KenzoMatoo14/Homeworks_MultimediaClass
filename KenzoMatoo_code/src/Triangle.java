import static java.lang.Math.sqrt;

public class Triangle extends Figures {
    private double side;

    public Triangle() {
        super();
        this.side = 0;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
        CalculateArea();       // Recalculate area whenever base changes
        CalculatePerimeter();  // Recalculate perimeter whenever base changes
    }


    // Overridden method to calculate area
    @Override
    public void CalculateArea() { //this is an equilateral triangle formula it considers all sides equal
        double area = (sqrt(3)*(side * side)) / 4;
        setArea(area);
    }

    // Overridden method to calculate perimeter for a right triangle
    @Override
    public void CalculatePerimeter() {
        double perimeter = side * 3;
        setPerimeter(perimeter);
    }
}
