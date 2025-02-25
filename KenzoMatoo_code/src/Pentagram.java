import static java.lang.Math.sqrt;

public class Pentagram extends Figures{
    private double side;

    public Pentagram() {
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
        //i also got this formula with the help of ChatGPT and internet
        //it basically uses the golden ratio to get the measure of the side of an outer pentagon which has the pentagram inscribed
        //and with that information and the formula it gets the area
        //(im not sure how though sorry, i didn't wanted to waste my time trying to understand the formula)
        double GoldenRatioPhi = (1 + sqrt(5))/2;
        double EdgeLenghtPentagon = side + (side / GoldenRatioPhi);
        double area = (sqrt(5 * (5 - 2 * sqrt(5))) * EdgeLenghtPentagon * EdgeLenghtPentagon) / 2;
        super.setArea(area);
    }

    @Override
    public void CalculatePerimeter() {
        double perimeter = 10 * side;
        setPerimeter(perimeter);
    }
}
