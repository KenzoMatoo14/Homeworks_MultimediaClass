public class CoordinateCalculator {
    public static String calculate(String conversionType, String xText, String yText, String rText, String thetaText) {
        try {
            // Cartesian to Polar conversion
            if ("Cartesian → Polar".equals(conversionType)) {
                double x = Double.parseDouble(xText); // Parse X coordinate
                double y = Double.parseDouble(yText); // Parse Y coordinate
                double r = Math.sqrt(x * x + y * y); // Calculate radius (r)
                double theta = Math.toDegrees(Math.atan2(y, x)); // Calculate angle (theta)
                return String.format("r = %.2f, θ = %.2f°", r, theta); // Return result

            } else {
                // Polar to Cartesian conversion
                double r = Double.parseDouble(rText); // Parse radius
                double theta = Math.toRadians(Double.parseDouble(thetaText)); // Convert theta to radians
                double x = r * Math.cos(theta); // Calculate X coordinate
                double y = r * Math.sin(theta); // Calculate Y coordinate
                return String.format("x = %.2f, y = %.2f", x, y); // Return result
            }
        } catch (NumberFormatException ex) {
            return "Invalid input!"; // Handle invalid input error
        }
    }
}
