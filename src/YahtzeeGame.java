// Name: Christian Coppoletti
// Instructor: Krumpe
// CSE 174, Section G
// Date: 11/29/16
// Filename: YahtzeeGame.java
// Description: Plays a game of Yahtzee for the user and prints scores


import java.util.Scanner; //gets input

public class YahtzeeGame {
   public static Scanner in = new Scanner(System.in);
   
   public static void main(String[] args){
      int[] dice = new int[5];
      String userIn = "";
      
      //prompts for user input and fires functions
      //also prints results
      pl("Welcome to Yahtzee!");
      for (int i = 1; i < 4; i++){
         if(i == 1) roll(dice);
         sort(dice);
         pl("Roll #"+i+": "+toString(dice));
         scoreChoices(dice);
         
         while(!userIn.equals("n") && !userIn.equals("y") && i != 3){
            p("Roll again?");
            userIn = in.next();
            if(userIn.equals("y")){
               reroll(dice);
               sort(dice);
            }
         }
         
         if(userIn.equals("n") || i == 3){
            pl("Your score is " + maxScore(dice) + ". Goodbye.");
            return;
         }
         //reset input for next loop
         userIn = "";
      }
   } //end main
   
   public static void roll(int[] dice){
      //assigns a random value to each item in the array
      for(int i = 0; i < dice.length; i++){
         dice[i] = (int) (Math.random() * 6) + 1;
      }
   }
   
   public static void sort(int[] dice){
      //typical bubble sort without any fancy optimization
      for(int c = 0; c < dice.length-1; c++){
         for(int i = 0; i < dice.length-1; i++){
            if(dice[i] > dice[i+1]){
               int temp = dice[i];
               dice[i] = dice[i+1];
               dice[i+1] = temp;
            }
         }
      }
   }
   
   public static String toString(int[] dice){
      //returns a formatted string of the array without line breaks
      String res = "";
      for(int n : dice)
         res += n + " ";
      return res;
   }
   
   public static void reroll(int[] dice){
      //selectively rerolls dice
      String userIn = "";
      //loops until user input is correct
      while(userIn.length() != 5 || userIn.indexOf("y") < 0 || checkYN(userIn)){
         p("Indicate which dice to roll using y and n: ");
         userIn = in.next();
      }
      //reassigns dice values
      for(int i = 0; i < dice.length; i++){
         if(userIn.charAt(i) == 'y')
            dice[i] = (int) (Math.random() * 6) + 1;
      }
   }
   
   public static boolean checkYN(String userIn){
      //simply checks for proper input for reroll
      for(int i = 0; i < userIn.length(); i++){
         if(userIn.charAt(i) != 'y' && userIn.charAt(i) != 'n')
            return true;
      }
      return false;
   }
   
   public static int yahtzee(int[] dice){
      //if all dice are the same value return 50
      int match = dice[0];
      for(int n : dice){
         if (match != n)
            return 0;
      }
      return 50;
   }
   
   public static int fourOfAKind(int[] dice){
      //counts the number of each dice value
      //if more than 3 match returns four of a kind score
      boolean fourOf = false;
      for(int n : dice){
         for (int i = 0, j = 0; i < dice.length; i++){
            if (n == dice[i]) 
               j++;
            if (j > 3) 
               fourOf = true;
         }
      }
      
      if(fourOf)
         return chance(dice);
      
      return 0;
   }
   
   public static int threeOfAKind(int[] dice){
      //works same as fourOfAKind only with 3 instead 
      boolean threeOf = false;
      for(int n : dice){
         for (int i = 0, j = 0; i < dice.length; i++){
            if (n == dice[i]) j++;
            if(j > 2) threeOf = true;
         }
      }
      
      if(threeOf)
         return chance(dice);
      
      return 0;
   }
   
   public static int largeStraight(int[] dice){
      //can't use array utils yet, so we have to compare the values manually
      //against the two possible large straights
      int[] option1 = {1,2,3,4,5};
      int[] option2 = {2,3,4,5,6};
      if(dice[0] == 1){
         for(int i = 0; i < dice.length; i++){
            if (dice[i] != option1[i])
               return 0;
         }
      }
      else{
         for(int i = 0; i < dice.length; i++){
            if (dice[i] != option2[i])
               return 0;
         }
      }
      return 40;
   }
   
   public static int smallStraight(int[] dice){
      //messy way of checking small straights
      //since sorting and duplicates screw with sequential scanning of
      //rolls like 1,2,2,3,4
      if(largeStraight(dice) > 0) return 30;
      for (int n : dice){
         if (n == 1){
            if(sum(dice, 2) > 0 && sum(dice, 3) > 0 && sum(dice, 4) > 0)
               return 30;
         } else if (n == 2) {
            if(sum(dice, 3) > 0 && sum(dice, 4) > 0 && sum(dice, 5) > 0)
               return 30;
         } else if (n == 3) {
            if(sum(dice, 4) > 0 && sum(dice, 5) > 0 && sum(dice, 6) > 0)
               return 30;
         }
      }
      return 0;
   }
   
   public static int fullHouse(int[] dice){
      //checks whether three of a kind exists
      //if it does check other two numbers
      //if they match return 25
      if(yahtzee(dice)>0) return 25;
      if(fourOfAKind(dice)>0) return 0;
      if(threeOfAKind(dice)>0){
         for(int i = 0; i < dice.length; i++){
            if(dice[i]*2 == sum(dice,dice[i])){
               return 25;
            }
         }
      }
      return 0;
   }
   
   public static int sum(int[] dice, int key){
      //checks sum of key numbers in dice
      //useful for searching as well
      int sum = 0;
      for(int n : dice){
         if (n == key) sum += n;
      }
      return sum;
   }
   
   public static int chance(int[] dice){
      //returns sum of all numbers in dice
      int sum = 0;
      for(int n : dice){
         sum += n;
      }
      return sum;
   }
   
   public static void scoreChoices(int[] dice){
      //lists out non-zero scores
      for(int i = 0; i < 7; i++){
         int s = sum(dice, i);
         if(s > 0)
            pl(i + " total: " + s);
      }
      
      if(threeOfAKind(dice) > 0) 
         pl("3 of a kind: " + threeOfAKind(dice));
      if(fourOfAKind(dice) > 0) 
         pl("4 of a kind: " + fourOfAKind(dice));
      if(fullHouse(dice) > 0) 
         pl("full house: 25");
      if(smallStraight(dice) > 0) 
         pl("small straight: 30");
      if(largeStraight(dice) > 0) 
         pl("large straight: 40");
      if(yahtzee(dice) > 0) 
         pl("yahtzee: 50");
      if(chance(dice) > 0) 
         pl("chance: " + chance(dice));
   }
   
   public static int maxScore(int[] dice){
      //crude way of checking for max score
      //would probably be easier with global vars
      //or somehow merged with the previous function
      int max = -1;
      if(yahtzee(dice) > max) 
         max = yahtzee(dice);
      if(fourOfAKind(dice) > max) 
         max = fourOfAKind(dice);
      if(threeOfAKind(dice) > max) 
         max = threeOfAKind(dice);
      if(largeStraight(dice) > max) 
         max = largeStraight(dice);
      if(smallStraight(dice) > max) 
         max = smallStraight(dice);
      if(fullHouse(dice) > max) 
         max = fullHouse(dice);
      if(chance(dice) > max) 
         max = chance(dice);
      return max;
   }
   
   public static void pl(String s){
      //because java is too cool for short print methods
      //TODO: Add different parameters with overloading
      //or just make a better class specifically for printing
      System.out.println(s);
   }
   
   public static void p(String s){
      //prints without a new line
      //TODO: Add different parameters with overloading
      //Who thought it was a good idea to have a 16 character print function call?
      System.out.print(s);
   }
   
} //end class YahtzeeGame
