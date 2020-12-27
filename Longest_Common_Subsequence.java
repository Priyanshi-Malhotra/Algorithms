public class Longest_Common_Subsequence {
    public static int longest_common_subsequence_recursive(String s1,String s2,int n,int m)
    {
        if(n==0 || m==0)
            return 0;
        if(s1.charAt(n-1)==s2.charAt(m-1))
            return (1+longest_common_subsequence_recursive(s1,s2,n-1,m-1));
        else
            return Math.max(longest_common_subsequence_recursive(s1,s2,n-1,m),longest_common_subsequence_recursive(s1,s2,n,m-1));
    }
    public static int longest_common_subsequence_dynamic_programming(String s1,String s2,int n,int m)
    {
        int[][] t=new int[n+1][m+1];
        for(int i=0;i<n+1;i++)
        {
            for(int j=0;j<m+1;j++)
            {
                if(i==0 || j==0)
                    t[i][j]=0;
                else if(s1.charAt(i-1)==s2.charAt(j-1))
                    t[i][j]=1+t[i-1][j-1];
                else
                    t[i][j]=Math.max(t[i][j-1],t[i-1][j]);
            }
        }
        return t[n][m];
    }
    public static String longest_common_subsequence(String s1,String s2,int n,int m)
    {
        int[][] t=new int[n+1][m+1];
        for(int i=0;i<n+1;i++)
        {
            for(int j=0;j<m+1;j++)
            {
                if(i==0 || j==0)
                    t[i][j]=0;
                else if(s1.charAt(i-1)==s2.charAt(j-1))
                    t[i][j]=1+t[i-1][j-1];
                else
                    t[i][j]=Math.min(t[i][j-1],t[i-1][j]);
            }
        }
        int i=n,j=m;
        String str="";
        while(i!=0 && j!=0)
        {
            if(s1.charAt(i-1)==s2.charAt(j-1))
            {
                str=s1.charAt(i-1)+str;
                i--;
                j--;
            }
            else if(t[i-1][j]>t[i][j-1])
                i--;
            else
                j--;
        }
        return str;
    }
    public static void main(String[] args)
    {
        String s1="abcde";
        String s2="ace";
        System.out.println("Length of longest common subsequence using recursion is : " + longest_common_subsequence_recursive(s1,s2,s1.length(),s2.length()));
        System.out.println("Length of longest common subsequence using dynamic programming is : " + longest_common_subsequence_dynamic_programming(s1,s2,s1.length(),s2.length()));
        System.out.println("Longest common subsequence is : " + longest_common_subsequence(s1,s2,s1.length(),s2.length()));
    }
}
