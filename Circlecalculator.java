import java.util.Scanner;

public class CircleCalculator {

    // Method to calculate the area of the circle
    public static double circleArea(double radius) {
        return Math.PI * radius * radius;
    }

    // Method to calculate the perimeter (circumference) of the circle
    public static double circlePerimeter(double radius) {
        return 2 * Math.PI * radius;
    }

    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the radius of the circle
        System.out.print("Enter the radius of the circle: ");
        double radius = scanner.nextDouble();

        // Calculate area and perimeter using the methods
        double area = circleArea(radius);
        double perimeter = circlePerimeter(radius);

        // Display the results
        System.out.printf("Area of the circle: %.2f\n", area);
        System.out.printf("Perimeter (circumference) of the circle: %.2f\n", perimeter);

        // Close the scanner
        scanner.close();
    }
}