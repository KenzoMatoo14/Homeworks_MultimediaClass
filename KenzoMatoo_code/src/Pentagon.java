import static java.lang.Math.sqrt;

public class Pentagon extends Figures{
    private double side;

    public Pentagon() {
        super();
        this.side = 0;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
        //calculates automatically the area and perimeter each time the side is set
        CalculateArea();
        CalculatePerimeter();
    }

    @Override
    public void CalculateArea() {
        //i got this formula from internet and ChatGPT, and it basically uses trigonometry and golden ratio to get this formula
        double area = .25*sqrt(5*(5+2*sqrt(5))) * (side * side);
        setArea(area);
    }

    @Override
    public void CalculatePerimeter() {
        double perimeter = side*5;
        setPerimeter(perimeter);
    }
}
