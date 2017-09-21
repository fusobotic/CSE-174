// Name: Christian Coppoletti
// Instructor: Krumpe
// CSE 174, Section G
// Date: 12/5/16
// Filename: Program13.java
// Description: Simulates a series of OCD students who mess with others' lockers


import java.util.Scanner; //gets input

public class Program13 {
   
   public static void main(String[] args){
      Scanner in = new Scanner(System.in);
      boolean[] lockers;
      int userInt = 0;
      String userString = "";
      
      //prevents user from having improper input
      while(userInt < 1){
         p("How many lockers? ");
         userInt = in.nextInt();
      }
      //false is closed, true is opened
      lockers = new boolean[userInt];

      //checks that users inputed y or n
      while(!userString.equals("y") && !userString.equals("n")){
         p("Show stages? (y/n)");
         userString = in.next();
      }
      //opens/closes lockers for each student then prints results
      for(int i = 0; i < lockers.length; i++){
         student(lockers, i);
         if(userString.equals("y")) printRes(lockers);
      }
      //checks final open locks and prints result
      checkOpen(lockers);
   } //end main
   
   public static void checkOpen(boolean[] lockers){
      //prints out which lockers are currently open by index
      p("Open: ");
      String res = "";
      //just prints the location of lockers that are open
      for(int i = 0; i < lockers.length; i++){
         if(lockers[i]) 
            res+= i+1 + ", ";
      }
      
      //removing the last comma and space
      int resI = res.lastIndexOf(", ");
      res = res.substring(0, resI);
      pl(res);
   }
   
   public static void student(boolean[] lockers, int i){
      //toggles lockers that are factors
      i++; //the student number
      for(int j = 0; j < lockers.length; j++){
         if((j+1)%i == 0) lockers[j] = !lockers[j];
      }
   }
   
   public static void printRes(boolean[] lockers){
      //prints current lockers graphically
      for(boolean b : lockers){
         if(b) p("O");
         else  p("-");
      }
      pl("");
   }
   
   public static void pl(String s){
      //because java is too cool for short print methods
      System.out.println(s);
   }
   
   public static void p(String s){
      //prints without a new line
      System.out.print(s);
   }
   
} //end class Program13
