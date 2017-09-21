class BubbleSort
{
   public static int counter = 0;
    public static void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++, counter++)
                if (arr[j] > arr[j+1])
                {
                    // swap temp and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
 
    /* Prints the array */
    public static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    public static int[] newArray(){
       int[] res = new int[50];
       for(int i = 50, j = 0; i > 0; i--, j++){
          res[j] = i;
       }
       return res;
    }
 
    // Driver method to test above
    public static void main(String args[])
    {
        BubbleSort ob = new BubbleSort();
        int arr[] = newArray();
        ob.bubbleSort(arr);
        System.out.println(counter);
        //ob.printArray(arr);
    }
}