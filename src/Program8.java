// Name: Christian Coppoletti
// Instructor: Krumpe
// CSE 174, Section G
// Date: 10/17/16
// Filename: Program8.java
// Description: Gives back info about a given date
//              also has an obscene number of methods


import java.util.Scanner; //gets input

public class Program8 {
   
   public static void main(String[] args){
      
      //prompts user for values
      Scanner in = new Scanner(System.in);
      
      //for tests, will get overwritten
      String date = "1-1-2016"; 
      int month, day, year;
      
      System.out.print("Enter a date: ");
      date = in.nextLine();
      //gets the entire next line of input
      
      //parsing out current data to ints
      month = parseMonth(date);
      day = parseDay(date);
      year = parseYear(date);
      
      if (!isValid(month,day,year)){
         //stopping if date isn't valid
         pl("This is not a valid date.");
         return;
      }
      
      //shortened print methods "pl(String);"
      //calling all those other methods and prints them
      pl(dateString(month,day,year));
      pl("Days in " + getMonthName(month) + ", " +
         year + ": " + daysInMonth(isLeapYear(year),month));
      pl("Days remaining in " + year + ": " +
         daysRemaining(month,day,year));
      
      //checks if leap year
      if (isLeapYear(year)) 
         pl("Leap year: yes");
      else
         pl("Leap year: no");
      
      pl("Next day: " + nextDateString(month,day,year));
      
      //PRAISE THE SUN IT WORKS
      
   } //end main
   
   
   public static int parseMonth(String date){
      //relative to first hyphen, parses the month
      int index = date.indexOf('-');
      return Integer.parseInt(date.substring(0,index));
   }
   
   public static int parseDay(String date){
      //function relative to the second hyphen, parses the day
      int index = date.indexOf('-');
      return Integer.parseInt(date.substring(index+1,date.indexOf('-',index+1)));
   }
   
   public static int parseYear(String date){
      //relative to the last hyphen, parses the year
      int index = date.lastIndexOf('-');
      return Integer.parseInt(date.substring(index+1));
   }
   
   
   public static boolean isLeapYear(int year){
      //returns whether the year is a leap year
      return year%4 == 0 && (year%100 != 0 || year%400 == 0);
   }
   
   public static int daysInMonth(boolean leap, int month){
      //returns the number of days in the month, leaps considered
      if (month==4 || month==6 || month==9 || month==11)
         return 30;
      else if (month == 2 && leap)
         return 29;
      else if (month == 2 && !leap)
         return 28;
      else if (month > 12)
         return -1;
      // if date is invalid it returns -1
      //returns 31 if it's a regular month
      return 31; 
   }
   
   public static boolean isValid(int month, int day, int year){
      //returns whether the date is valid
      int dIM = daysInMonth(isLeapYear(year), month);
      boolean validDays = dIM != -1;
      return validDays && month <= 12 && year >= 1600 && day <= dIM;
   }
   
   public static int daysRemaining(int month, int day, int year){
      //determines and returns days remaining in year
      boolean leap = isLeapYear(year);
      int totalDays = 365;
      if (leap) totalDays = 366;
      
      if (!isValid(month,day,year)) 
         return -1;
      // if date is invalid it returns -1
      
      //first subtract out months from totalDays
      for (int m = 1; m < month; m++){
         totalDays -= daysInMonth(leap,m);
      }
      
      //then return days left with current day included
      return totalDays - day + 1;
   }
   
   public static int dayOfWeek(int month, int day, int year){
      //why can't we use built in libraries?
      //just made me google harder ø\_(>.>)_/ø :
      //https://forum.processing.org/two/discussion/3350/getting-the-day-of-the-week-without-calendar
      
      //method returns a 0-6 index of the day of the week
      
      if (!isValid(month,day,year)) 
         return -1;
      // if date is invalid it returns -1
      
      if (month < 3) {
         month += 12;
         year--;
      }
      //this fomula makes me want to use Stardates ffs
      return (day + (int)((month+1)*2.6) +  year + (int)(year/4)
                 + 6*(int)(year/100) + (int)(year/400) + 6) % 7;
   }
   
   public static String getDayName(int day){
      //recieves an index and returns a day of the week string
      switch(day){
         case 0: return "Sunday";
         case 1: return "Monday";
         case 2: return "Tuesday";
         case 3: return "Wednesday";
         case 4: return "Thursday";
         case 5: return "Friday";
         case 6: return "Saturday";
         default:return "ERROR";
         // if day is invalid it returns error
      }
   }
   
   public static String getMonthName(int month){
      //recieves an index and returns a month string
      switch(month){
         case 1: return "January";
         case 2: return "February";
         case 3: return "March";
         case 4: return "April";
         case 5: return "May";
         case 6: return "June";
         case 7: return "July";
         case 8: return "August";
         case 9: return "September";
         case 10:return "October";
         case 11:return "November";
         case 12:return "December";
         default:return "ERROR";
         // if month is invalid it returns error
      }
   }
   
   public static String dateString(int month, int day, int year){
      //returns formatted date in the following format:
      //"Monday, October 17, 2016"
      if (!isValid(month,day,year)) return "ERROR";
      //if date is invalid it returns error
      
      return getDayName(dayOfWeek(month,day,year)) + ", " +
         getMonthName(month) + " " + day + ", " + year;
   }
   
   public static String nextDateString(int month, int day, int year){
      //same as previous method only it prints the next day
      if (!isValid(month,day,year)) return "ERROR";
      //if date is invalid it returns error
      
      //checks if day is on edge of the month or year and handles it
      if (daysRemaining(month,day,year) == 1){
         month = 1;
         day = 1;
         year++;
      }
      else if (day+1 > daysInMonth(isLeapYear(year),month)){
         month++;
         day = 1;
      } else {
         day++;
      }
      
      return getDayName(dayOfWeek(month,day,year)) + ", " +
         getMonthName(month) + " " + day + ", " + year;
      
      //     Dawn of 
      //  The Second Day
      //-48 Hours Remain-
   }
   
   public static void pl(String s){
      //because java is too cool for short print methods
      System.out.println(s);
   }
   
} //end class Program8
