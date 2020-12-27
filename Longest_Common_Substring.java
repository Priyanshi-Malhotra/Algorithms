public class Longest_Common_Substring {
    public static int longest_common_substring(String s1,String s2,int n,int m)
    {
        int[][] t=new int[n+1][m+1];
        int max=0;
        for(int i=0;i<n+1;i++)
        {
            for(int j=0;j<m+1;j++)
            {
                if(i==0 || j==0)
                    t[i][j]=0;
                else if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    t[i][j] = 1 + t[i - 1][j - 1];
                    max= Math.max(max,t[i][j]);
                }
                else
                    t[i][j]=0;
            }
        }
        return max;
    }
    public static void main(String[] args)
    {
        String s1="abcdefh";
        String s2="cde";
        System.out.println("Length of longest common substring is : " + longest_common_substring(s1,s2,s1.length(),s2.length()));
    }
}
