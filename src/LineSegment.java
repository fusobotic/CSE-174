// Name: Christian Coppoletti
// Instructor: Krumpe
// CSE 174, Section G
// Date: 9/11/16
// Filename: LineSegment.java
// Description: finds data about a line segment

import java.util.Scanner; //gets input

public class LineSegment {
   
   public static void main(String[] args){

      //prompts user for values
      Scanner input = new Scanner(System.in);
      double x1, y1, x2, y2, xMid, yMid, slope;
      System.out.print("Point 1: Enter x and y: ");
      x1 = input.nextDouble();
      y1 = input.nextDouble();
      System.out.print("Point 2: Enter x and y: ");
      x2 = input.nextDouble();
      y2 = input.nextDouble();

      //calculates values
      xMid = (x1+x2)/2;
      yMid = (y1+y2)/2;
      slope = (y2-y1)/(x2-x1);

      //output results
      System.out.print("Your points: (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")\n");
      System.out.print("Midpoint: (" + xMid + ", " + yMid + ")\n");
      System.out.print("Slope: " + slope);
      
  } //end main
   
} //end class LineSegment