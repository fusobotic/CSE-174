// Name: Christian Coppoletti
// Instructor: Krumpe
// CSE 174, Section G
// Date: 11/11/16
// Filename: PrimeStuff.java
// Description: Gives info about prime numbers and their factors


import java.util.Scanner; //gets input

public class PrimeStuff {
   
   //global scanner
   static Scanner in = new Scanner(System.in);
   
   public static void main(String[] args){
      //lists initial user options and runs functions
      //according to number chosen, also loops until quit
      int menuChoice = 0;
      while(menuChoice != 4){
         pl("What would you like to do?");
         pl("1) Check if a number is prime");
         pl("2) Factor a number");
         pl("3) List prime numbers");
         pl("4) Quit");
         pr("Choice: ");
         menuChoice = in.nextInt();
         //switches can't take longs for some reason?
         switch(menuChoice){ 
            case 1: primeInput(); break;
            case 2: factors(); break;
            case 3: listInput(); break;
            case 4: return;
            default: pl("**** INVALID OPTION ****"); break;
         }
      }
   } //end main
   
   public static void primeInput(){
      //gets input from user for checking primes
      long userNum = getNum();
      //checks if number is prime and tells user
      if(isPrime(userNum)){
         pl("---> " + userNum + " is prime.");
         return;
      } 
      pl("---> " + userNum + " is not prime.");
   }

   public static boolean isPrime(long n){
      //checks divisibility with numbers lower than root of n
      if(n == 1) return false; //1 isn't prime
      if(n != 2 && n%2 == 0) return false; //for evens
      if(n%3 == 0) return false; //for multiples of 3

      long root = (long)Math.sqrt(n);
      //if n is not prime it has factors less than its root
      for(long i = 3; i <= root; i+=2){
         //only checks odd numbers
         if(n%i == 0) return false;
      }
      return true;
   }
   
   public static void factors(){
      //gets prime factors of number
      long userNum = getNum();

      pr("---> " + userNum + " = ");
      if (userNum == 1 || isPrime(userNum)) pr(userNum + "");
      else {
         for (long i = 2; i <= userNum; i++){
            while(userNum % i == 0){
               pr (i + "");
               if(userNum/i >= 2) pr(" * ");
               userNum /= i;
            }
         }
      }
      pl("");
   }
   
   public static void listInput(){
      //gets user input for list of prime numbers
      long userNum = getNum(), numOfPrime = 0, perRow = 0;

      while(numOfPrime < 1 || numOfPrime > 1000){
         pr("How many primes (1-1000): ");
         numOfPrime = in.nextLong();
      }

      while(perRow < 1 || perRow > 20){
         pr("How many primes per row (1-20): ");
         perRow = in.nextLong();
      }
      
      //finding the length of the largest prime number in list
      long maxLength = maxNum(userNum, numOfPrime);
      //calling function to actually print list
      printList(maxLength, userNum, numOfPrime, perRow);
   }
   
   public static void printList(long maxLength, long userNum, long numOfPrime, long perRow){
      //method exists because "methods can't be >30 lines"
      long curRow = 0;
      for(long i = userNum, results = 0; results < numOfPrime && i < Long.MAX_VALUE; i++){
         //stops listing primes when i reaches long max value
         //creates line breaks at row limit (perRow)
         if(curRow == perRow){
            pl("");
            curRow = 0;
         }
         
         //weird formatting because we can't use arrays yet
         if(isPrime(i)){
            long spaces = maxLength - (i+"").length();
            pr("  ");
            //makes sure there's right alignment by adding spaces
            for(long s = 0; s < spaces; s++) pr(" ");
            pr(i+"");
            results++;
            curRow++;
         }
      }
      pl("");
   }
   
   public static long maxNum(long userNum, long numOfPrime){
      //this method gets the length of the longest prime string
      //just for formatting things properly
      //because we can't use arrays yet...
      long maxLength = Long.MIN_VALUE;
      for(long i = userNum, results = 0; results < numOfPrime && i < Long.MAX_VALUE; i++){
         if(isPrime(i)){
            if (i > maxLength) maxLength = i;
            results++;
         }
      }
      return (maxLength+"").length();
   }
   
   public static long getNum(){
      //shorthand for prompting user for typical long number
      //it doesn't like numbers out of long's range though-
      //haven't taught us how to handle Input Mismatches yet...
      long userNum = 0;
      while(userNum < 1 || userNum > Long.MAX_VALUE){
         pr("Enter a number between 1 and " + Long.MAX_VALUE + ": ");
         userNum = in.nextLong();
      }
      return userNum;
   }
   
   public static void pl(String s){
      //because java is too cool for short print methods
      System.out.println(s);
   }
   
   public static void pr(String s){
      //prints without a new line
      System.out.print(s);
   }
   
} //end class PrimeStuff
