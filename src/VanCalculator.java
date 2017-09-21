// Name: Christian Coppoletti
// Instructor: Krumpe
// CSE 174, Section G
// Date: 9/12/16
// Filename: VanCalculator.java
// Description: Finds out how many vans we need

// Test Cases:
// These work fine: 0, 1, 19, 20, 21, 35, 40, 41, 50, 59, 60, 2147483647
// Values that break: 2147483648 and up (only because of int MAX_VALUE)

import java.util.Scanner; //gets input

public class VanCalculator {
   
   public static void main(String[] args){
      
      Scanner input = new Scanner(System.in);
      final double VAN_SIZE = 20;
      int people, newVans;
      double vansNeeded;
      
      //prompts user for values
      System.out.println("Vans hold 20 people.");
      System.out.print("How many people need a ride?");
      people = input.nextInt();
      
      vansNeeded = people/VAN_SIZE;
      newVans = (int) (vansNeeded + .999999);
      //casting value to int since vans don't come in fractions
      //.999999 makes van number go up one if not evenly divided
      
      
      //old method of calculating, only worked with a remainder
      //int emptySeats = VAN_SIZE-(people%VAN_SIZE);
      //vansNeeded = ((people+emptySeats)/VAN_SIZE);
      
      //gives the user the value
      System.out.print("You need " + newVans + " vans.");
      
   } //end main
   
} //end class VanCalculator