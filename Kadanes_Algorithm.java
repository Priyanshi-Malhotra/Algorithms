public class Kadanes_Algorithm {
    public static int max_sum_subarray(int[] arr)
    {
        int max_so_far=0,max_current=0;
        for(int i=0;i<arr.length;i++)
        {
            max_current=max_current+arr[i];
            if(max_so_far<max_current)
                max_so_far=max_current;
            if(max_current<0)
                max_current=0;
        }
        return max_so_far;
    }
    public static void main(String[] args)
    {
        int[] arr={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("Maximum sum of a subarray : " + max_sum_subarray(arr));
    }
}
