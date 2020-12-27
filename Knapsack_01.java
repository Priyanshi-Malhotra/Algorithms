public class Knapsack_01 {
    public static int max_value_recursion(int[] value,int[] weight,int w,int n)
    {
        if(n==0 || w==0)
            return 0;
        if(weight[n-1]<=w)
            return (Math.max(value[n-1]+max_value_recursion(value,weight,w-weight[n-1],n-1),max_value_recursion(value,weight,w,n-1)));
        else
            return max_value_recursion(value,weight,w,n-1);
    }
    public static int max_value_dynamic_programming(int[] value,int[] weight,int w,int n)
    {
        int[][] t=new int[n+1][w+1];
        for(int i=0;i<n+1;i++)
        {
            for(int j=0;j<w+1;j++)
            {
                if(i==0 || j==0)
                    t[i][j]=0;
                else if(weight[i-1]<=j)
                    t[i][j]=Math.max(value[i-1]+t[i-1][j-weight[i-1]],t[i-1][j]);
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[n][w];
    }
    public static boolean subset_sum(int[] arr,int sum)
    {
        boolean[][] t=new boolean[arr.length+1][sum+1];
        for(int i=0;i<arr.length+1;i++)
        {
            for(int j=0;j<sum+1;j++)
            {
                if((i==0 && j==0) || (i!=0 && j==0))
                    t[i][j]=true;
                else if(i==0)
                    t[i][j]=false;
                else if(arr[i-1]<=j)
                    t[i][j]=t[i-1][j-arr[i-1]] || t[i-1][j];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[arr.length][sum];
    }
    public static boolean equal_sum_partition(int[] arr,int sum)
    {
        if(sum%2!=0)
            return false;
        return subset_sum(arr,sum/2);
    }
    public static int subset_sum_count(int[] arr,int sum)
    {
        int[][] t=new int[arr.length+1][sum+1];
        for(int i=0;i<arr.length+1;i++)
        {
            for(int j=0;j<sum+1;j++)
            {
                if((i==0 && j==0) || (i!=0 && j==0))
                    t[i][j]=1;
                else if(i==0)
                    t[i][j]=0;
                else if(arr[i-1]<=j)
                    t[i][j]=t[i-1][j-arr[i-1]] + t[i-1][j];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[arr.length][sum];
    }
    public static void main(String[] args)
    {
        int[] value={60,100,120};
        int[] weight={10,20,30};
        int w=50,n=3;
        System.out.println("Maximum value through recursion is : " + max_value_recursion(value,weight,w,n));
        System.out.println("Maximum value through dynamic programming is : " + max_value_dynamic_programming(value,weight,w,n));
        int sum=100;
        if(subset_sum(weight,sum))
            System.out.println("There exists a subset with sum " + sum + ".");
        else
            System.out.println("There does not exist a subset with sum " + sum + ".");
        sum=60;
        if(equal_sum_partition(weight,sum))
            System.out.println("It can be divided into two subsets with equal sum of " + sum + ".");
        else
            System.out.println("It cannot be divided into two subsets with equal sum of " + sum + ".");
        int[] weightt={2,3,5,8,6,10};
        sum=8;
        System.out.println("Number of subsets with sum " + sum + " are : " + subset_sum_count(weightt,sum));
    }
}
