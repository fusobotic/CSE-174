// Name: Christian Coppoletti
// Instructor: Krumpe
// CSE 174, Section G
// Date: 9/25/16
// Filename: ContactParse.java
// Description: Converts user info into neater variables and prints them

// Names: 
// SMITH    ,      Mary jean > Mary J. Smith
// smith , mary jean > Mary J. Smith
//    COPPOLETTI   , CHRIS MICHAEL   > Chris M. Coppoletti

// Numbers:
// 614-929-7479 > (614)929-7479
// 5    1  3   -     5  2  9     -        1  80  9 > (513)529-1809
//   8gasd3!lkjf5 7jgur6asd3 0gh8a7rt8 > (835)763-0878
// 5148769999 > (514)876-9999

// Addresses:
// 204 Main Street, Apt. 5, Oxford, Ohio 45056 >
// 204 Main Street, Apt. 5
// Oxford, Ohio 45056
//    1157 Parkside Dr., Batavia, OH 45103     >
// 1157 Parkside Dr.
// Batavia, OH 45103
//   123 Main St, Oxford, OH 45067 >
// 123 Main St
// Oxford, OH 45067



import java.util.Scanner; //gets input

public class ContactParse {
   
   public static void main(String[] args){

      //prompts user for values
      Scanner in = new Scanner(System.in);
      String userName, userPhone, userAddress, //initial line input
             lastName, firstName, midInitial, parsedPhone, streetAddress, city; 
             //converted info

      int index = 0; //useful for storing position of spaces or commas

      System.out.print("Name: "); 
      userName = in.nextLine(); //storing all this from lines
      System.out.print("Phone: ");
      userPhone = in.nextLine();
      System.out.print("Address: ");
      userAddress = in.nextLine();

      System.out.println("\n\n***** Results *****");

      //goes through the line and separates strings
      userName = userName.toLowerCase().trim(); //lowercase and no edge spaces
      userName = userName.replaceAll("\\s+", " "); //gets rid of multi-spaces
      index = userName.indexOf(" ");
      lastName = userName.substring(0, index);
      index = userName.indexOf(" ", index+1); //looking for next space
      firstName = userName.substring(index+1, userName.indexOf(" ", index+1));
      index = userName.indexOf(" ", index+1); //looking for last space
      midInitial = userName.substring(index+1, index+2).toUpperCase();

      //uppercasing the first letter and printing
      lastName = lastName.substring(0,1).toUpperCase() 
               + lastName.substring(1);
      firstName = firstName.substring(0,1).toUpperCase() 
                + firstName.substring(1);
      System.out.println(firstName + " " + midInitial + ". " + lastName);


      //formatting the phone number is easiest
      userPhone = userPhone.replaceAll("[^\\d]", ""); //leaves us with only numbers
      parsedPhone = "(" + userPhone.substring(0,3) + ")" 
                  + userPhone.substring(3,6) + "-" + userPhone.substring(6);
      System.out.println(parsedPhone);

      userAddress = userAddress.trim();
      //printing street address with new line break in place of comma
      index = userAddress.lastIndexOf(",", userAddress.lastIndexOf(",")-1);
      //gets us the comma breaking city from street address
      city = userAddress.substring(index+2);
      streetAddress = userAddress.substring(0, index);
      System.out.println(streetAddress);
      System.out.println(city);

  } //end main
   
} //end class ContactParse
