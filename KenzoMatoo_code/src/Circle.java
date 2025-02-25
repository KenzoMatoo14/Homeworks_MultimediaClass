public class Circle extends Figures {
    //unique attribute to circle class
    private double radius;

    public Circle() {
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
        double area = Math.PI * radius * radius; //standard formula for a circle area
        setArea(area);
    }

    @Override
    public void CalculatePerimeter() {
        double perimeter = 2 * Math.PI * radius; //standard formula for a circle perimeter
        setPerimeter(perimeter);
    }

}
