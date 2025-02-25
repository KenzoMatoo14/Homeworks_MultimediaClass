public class Rectangle extends Figures {
    private double base;
    private double height;

    // Constructor
    public Rectangle() {
        super();
        this.base = 0;
        this.height = 0;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        CalculateArea();       // Recalculate area whenever height changes
        CalculatePerimeter();  // Recalculate perimeter whenever height changes
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
        CalculateArea();       // Recalculate area whenever base changes
        CalculatePerimeter();  // Recalculate perimeter whenever base changes
    }


    @Override
    public void CalculateArea() {
        double area = base * height;
        setArea(area); // Update the area in the parent class
    }

    @Override
    public void CalculatePerimeter() {
        double perimeter = 2 * (base + height);
        setPerimeter(perimeter); // Update the perimeter in the parent class
    }
}
