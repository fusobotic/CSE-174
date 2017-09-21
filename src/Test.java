/**
 * Auto Generated Java Class.
 */
import java.util.*;
public class Test {
   
   
   public static void main(String[] args) { 
      System.out.print(maxRun(new int[] {4,5}));
   }
   
   
   public static int maxRun(int[] nums) {
      int max = 0;
      for(int i = 0; i < nums.length; i++){
         int curCount = 0;
         for(int j = i; j < nums.length; j++){
            if(nums[i] == nums[j]) curCount++;
            else{
               if(curCount == 1) curCount = 0;
               break;
            } 
         }
         if(curCount > max) max = curCount;
      }
      return max;
   }
   
   
   
   
   /* ADD YOUR CODE HERE */
   
}
