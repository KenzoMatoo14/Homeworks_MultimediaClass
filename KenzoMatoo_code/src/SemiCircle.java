public class SemiCircle extends Figures {
    private double radius;

    public SemiCircle() {
        super();
        this.radius = 0;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        //calculates automatically the area and perimeter each time the radius is set
        CalculateArea();
        CalculatePerimeter();
    }

    @Override
    public void CalculateArea() {
        //normal circle area formula divided by two
        double area = (Math.PI * radius * radius) / 2;
        setArea(area);
    }

    @Override
    public void CalculatePerimeter() {
        double perimeter = radius * (Math.PI + 2);
        setPerimeter(perimeter);
    }
}
