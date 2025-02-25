public class AspectRatioCalculator {

    // Calculates and returns the aspect ratio as a string
    public static String calculateAspectRatio(int width, int height) {
        int gcdValue = gcd(width, height); // Find greatest common divisor
        int aspectWidth = width / gcdValue;
        int aspectHeight = height / gcdValue;
        return aspectWidth + ":" + aspectHeight; // Return formatted aspect ratio
    }

    // Computes greatest common divisor using Euclidean algorithm
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
