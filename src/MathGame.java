// Name: Christian Coppoletti
// Instructor: Krumpe
// CSE 174, Section G
// Date: 10/2/16
// Filename: MathGame.java
// Description: User has to guess multiplication and division problems

import java.util.Scanner; //gets input

public class MathGame {
   
   // need these vars outside main to reference in other methods
   public static int multiCorrect, divisCorrect;
   public static Scanner in = new Scanner(System.in);
   
   public static void main(String[] args){
      
      int multiLimit, divisLimit;
      double multiScore, divisScore, finalScore;
      long finishTime = 0;
      multiCorrect = 0; 
      divisCorrect = 0;
      // need to make sure these static vars are zeroed
      
      System.out.println("Welcome to my math quiz!");
      System.out.println("This quiz will give you three multiplication problems,");
      System.out.println("and then three division problems.");
      System.out.println("-----------------------------------------------------");
      // sets the limits that we need
      System.out.print("Enter the multiplication limit: ");
      multiLimit = in.nextInt();
      System.out.print("Enter the division limit: ");
      divisLimit = in.nextInt();
      
      
      System.out.println("\nThe timer starts...now!\n");
      // stores current system clock time
      long startTime = System.currentTimeMillis();
      System.out.println("-MULTIPLICATION--------------------------------------");
      // call function 3 times for this portion
      multiplication(multiLimit);
      multiplication(multiLimit);
      multiplication(multiLimit);
      
      System.out.println("\n-DIVISION--------------------------------------------");
      // call division function 3 times
      division(divisLimit);
      division(divisLimit);
      division(divisLimit);
      
      // subtracts current time from start time and converts to seconds
      finishTime = (System.currentTimeMillis() - startTime) / 1000;
      System.out.println("\nThe timer stops...now!  You have answered in " 
                            + finishTime + " seconds.");
      
      System.out.println("\n-RESULTS---------------------------------------------");
      multiScore = multiCorrect/3.0*100.0;
      divisScore = divisCorrect/3.0*100.0;
      finalScore = (multiCorrect+divisCorrect)/6.0*100.0;
      
      // print the scores with some fancy formatting
      System.out.printf("Multiplication score: %d out of 3 (%.3f%%)%n", 
                        multiCorrect, multiScore);
      System.out.printf("Division score: %d out of 3 (%.3f%%)%n", 
                        divisCorrect, divisScore);
      System.out.printf("Overall score: %d out of 6 (%.3f%%)%n", 
                        (multiCorrect+divisCorrect), finalScore);
   } // end main
   
   public static void multiplication(int max){
      // generates numbers up to the max
      int firstNum = (int)(1 + max * Math.random());
      int secondNum = (int)(1 + max * Math.random());
      
      if(firstNum * secondNum >= max){
         // totally not a while loop :)
         // if random values aren't workable recalls method
         multiplication(max);
      }
      else {
         int answer = firstNum * secondNum;
         System.out.print(firstNum + " * " + secondNum + " = ");
         // checks if user input matches answer
         if(in.nextInt() == answer){
            multiCorrect++;
            System.out.print("Yes!");
         } 
         else {
            System.out.print("Sorry, " + firstNum + " * " + secondNum + " = " + answer + ".");
         }
         
         System.out.println(" You have " + multiCorrect + " correct so far.");
      }
   } // end multiplication
   
   public static void division(int max){
      int firstNum = (int)(1 + max * Math.random());
      int secondNum = (int)(1 + max * Math.random());
      
      // checks our remainder and makes sure the two don't match
      if(firstNum == secondNum || firstNum%secondNum != 0){
         division(max);
      }
      else {
         int answer = firstNum/secondNum;
         System.out.print(firstNum + " / " + secondNum + " = ");
         // checks if user input matches answer and gives correct solution
         if(in.nextInt() == answer){
            divisCorrect++;
            System.out.print("Yes!");
         } 
         else {
            System.out.print("Sorry, " + firstNum + " / " + secondNum + " = " + answer + ".");
         }
         System.out.println(" You have " + (multiCorrect+divisCorrect) + " correct so far.");
      }
   } // end division
   
} // end class ContactParse
