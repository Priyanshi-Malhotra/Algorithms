public class Unbounded_Knapsack {
    public static int rod_cutting(int[] length,int[] value,int n)
    {
        int[][] t=new int[length.length+1][n+1];
        for(int i=0;i<length.length+1;i++)
        {
            for(int j=0;j<n+1;j++)
            {
                if(i==0 || j==0)
                    t[i][j]=0;
                else if(length[i-1]<=j)
                    t[i][j]=Math.max(value[i-1]+t[i][j-length[i-1]],t[i-1][j]);
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[length.length][n];
    }
    public static int coin_change(int[] value,int n)
    {
        int[][] t=new int[value.length+1][n+1];
        for(int i=0;i<value.length+1;i++)
        {
            for(int j=0;j<n+1;j++)
            {
                if((i==0 && j==0) || (i!=0 && j==0))
                    t[i][j]=1;
                else if(i==0)
                    t[i][j]=0;
                else if(value[i-1]<=j)
                    t[i][j]=t[i-1][j-value[i-1]]+t[i-1][j];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[value.length][n];
    }
    public static void main(String[] args)
    {
        int[] length={1,2,3,4,5,6,7,8};
        int[] value={1,5,8,9,10,17,17,20};
        int n=8;
        System.out.println("Number of ways to get value " + n  + " is : "+ coin_change(value,n));
        System.out.println("Maximum value after cutting the rod of length  " + n + " is : " + rod_cutting(length,value,n));
    }
}
