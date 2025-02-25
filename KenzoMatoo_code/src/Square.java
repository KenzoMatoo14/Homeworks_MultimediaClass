public class Square extends Figures {
    private double sideLength;

    public Square(){
        super();
        this.sideLength = 0;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
        CalculateArea();       // Recalculate area whenever side length changes
        CalculatePerimeter();  // Recalculate perimeter whenever side length changes
    }

    // Overridden methods for area and perimeter calculation
    @Override
    public void CalculateArea() {
        double area = sideLength * sideLength;
        setArea(area); // Update the area in the parent class
    }

    @Override
    public void CalculatePerimeter() {
        double perimeter = 4 * sideLength;
        setPerimeter(perimeter); // Update the perimeter in the parent class
    }


}
