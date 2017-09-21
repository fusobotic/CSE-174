// Name: Christian Coppoletti
// Instructor: Krumpe
// CSE 174, Section G
// Date: 9/19/16
// Filename: TriangleMath.java
// Description: Calculates and returns info about a user's triangle

import java.util.Scanner; //gets input

public class TriangleMath {
   
   public static void main(String[] args){
      
      // initializing all needed variables
      Scanner input = new Scanner(System.in);
      String triangleName;
      double x1, y1, x2, y2, x3, y3, side1, side2, side3, 
         perimeter, area, centroidX, centroidY, incirlceRadius, incircleArea;

      // user inputs triangle name and we uppercase it
      System.out.print("Enter a three letter name for your triangle: ");
      triangleName = input.next();
      triangleName = triangleName.toUpperCase();

      // user inputs vertices and we assign them to variables
      System.out.print("Coordinates of vertex " + triangleName.charAt(0) + ": ");
      x1 = input.nextDouble();
      y1 = input.nextDouble();
      System.out.print("Coordinates of vertex " + triangleName.charAt(1) + ": ");
      x2 = input.nextDouble();
      y2 = input.nextDouble();
      System.out.print("Coordinates of vertex " + triangleName.charAt(2) + ": ");
      x3 = input.nextDouble();
      y3 = input.nextDouble();

      // calculating side length
      side1 = Math.sqrt(Math.pow((x2-x1), 2.0) + Math.pow((y2-y1), 2.0));
      side2 = Math.sqrt(Math.pow((x3-x2), 2.0) + Math.pow((y3-y2), 2.0));
      side3 = Math.sqrt(Math.pow((x1-x3), 2.0) + Math.pow((y1-y3), 2.0));

      // printing side length
      System.out.println("\n--- Side lengths ---");
      System.out.printf("" + triangleName.charAt(0) + triangleName.charAt(1) + ": %.3f\n", side1);
      System.out.printf("" + triangleName.charAt(1) + triangleName.charAt(2) + ": %.3f\n", side2);
      System.out.printf("" + triangleName.charAt(2) + triangleName.charAt(0) + ": %.3f\n\n", side3);

      // calculating all the other measurements with appropriate formulas
      perimeter = side1 + side2 + side3;
      area = Math.abs((x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2))/2);
      centroidX = (x1+x2+x3)/3;
      centroidY = (y1+y2+y3)/3;
      incirlceRadius = Math.abs(area/(perimeter/2));
      incircleArea = Math.abs(Math.PI*Math.pow(incirlceRadius, 2.0));

      // printing the previous calculations
      System.out.println("--- Other measures ---");
      System.out.printf("Perimeter       = %.3f\n", perimeter);
      System.out.printf("Area            = %.3f\n", area);
      System.out.printf("Centroid        = (%.3f, %.3f)\n", centroidX, centroidY);
      System.out.printf("Incircle radius = %.3f\n", incirlceRadius);
      System.out.printf("Incircle area   = %.3f", incircleArea);

   } // end main
   
} // end class TriangleMath