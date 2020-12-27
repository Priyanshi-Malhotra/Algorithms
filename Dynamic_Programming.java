public class Dynamic_Programming {
    public static int recurrence_recursive(int n)
    {
        int sum=0;
        if(n==0 || n==1)
            return 2;
        for(int i=1;i<n;i++)
            sum+=2*recurrence_recursive(i)*recurrence_recursive(i-1);
        return sum;
    }
    static int[] t;
    public static int recurrence_memoization(int n)
    {
        t=new int[n+1];
        t[0]=t[1]=2;
        for(int i=2;i<n+1;i++)
        {
            for(int j=1;j<i;j++)
                t[i]+=2*t[j]*t[j-1];
        }
        return t[n];
    }
    public static int recurrence_dp(int n)
    {
        t=new int[n+1];
        t[0]=t[1]=2;
        t[2]=2*t[1]*t[0];
        for(int i=3;i<n+1;i++)
            t[i]=t[i-1]+2*t[i-1]*t[i-2];
        return t[n];
    }
    public static int max_sum(int[] arr)
    {
        int max=0,sum=0;
        for(int i=0;i<arr.length;i++)
        {
            sum=Math.max(sum+arr[i],arr[i]);
            max=Math.max(max,sum);
        }
        return max;
    }
    public static int max_sum2(int[] arr)
    {
        int current_sum=0,max_sum=0;
        for(int i=0;i<arr.length;i++)
        {
            current_sum+=arr[i];
            if(current_sum>max_sum)
                max_sum=current_sum;
            if(current_sum<0)
                current_sum=0;
        }
        return max_sum;
    }
    public static int max_sum_with_no_two_contagious(int[] arr)
    {
        t=new int[arr.length];
        t[0]=arr[0];
        t[1]=Math.max(arr[0],arr[1]);
        for(int i=2;i<arr.length;i++)
            t[i]=Math.max(arr[i]+t[i-2],t[i-1]);
        return t[arr.length-1];
    }
    public static int max_with_no_three_contagious(int[] arr)
    {
        t=new int[arr.length];
        t[0]=arr[0];
        t[1]=Math.max(arr[0]+arr[1],arr[1]);
        t[2]=Math.max(arr[0]+arr[1],Math.max(arr[0]+arr[2],arr[1]+arr[2]));
        for(int i=3;i<arr.length;i++)
            t[i]=Math.max(arr[i]+arr[i-1]+t[i-3],Math.max(arr[i]+t[i-2],t[i-1]));
        return t[arr.length-1];
    }
    public static int catalan_number(int n)
    {
        if(n==0)
            return 1;
        int sum=0;
        for(int i=1;i<n+1;i++)
            sum+=catalan_number(i-1)*catalan_number(n-i);
        return sum;
    }
    public static int catalan_number_memoization(int n)
    {
        t=new int[n+1];
        t[0]=1;
        for(int i=1;i<n+1;i++)
        {
            for(int j=1;j<i+1;j++)
            {
                t[i]+=t[j-1]*t[i-j];
            }
        }
        return t[n];
    }
    public static int unbounded_knapsack(int[] size,int[] values,int capacity)
    {
        int[][] t=new int[size.length+1][capacity+1];
        for(int i=0;i<size.length+1;i++)
        {
            for(int j=0;j<capacity+1;j++)
            {
                if(i==0 || j==0)
                    t[i][j]=0;
                else if(size[i-1]<=j)
                    t[i][j]=Math.max(values[i-1]+t[i][j-size[i-1]],t[i-1][j]);
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[size.length][capacity];
    }
    public static int knapsack_01(int[] size,int[] values,int capacity)
    {
        int[][] t=new int[size.length+1][capacity+1];
        for(int i=0;i<size.length+1;i++)
        {
            for(int j=0;j<capacity+1;j++)
            {
                if(i==0 || j==0)
                    t[i][j]=0;
                else if(size[i-1]<=j)
                    t[i][j]=Math.max(values[i-1]+t[i-1][j-size[i-1]],t[i-1][j]);
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[size.length][capacity];
    }
    public static int coin_change(int[] coins,int amount)
    {
        int[][] t=new int[coins.length+1][amount+1];
        for(int i=0;i<coins.length+1;i++)
        {
            for(int j=0;j<amount+1;j++)
            {
                if(i==0 && j!=0)
                    t[i][j]=0;
                else if(j==0)
                    t[i][j]=1;
                else if(coins[i-1]<=j)
                    t[i][j]=t[i][j-coins[i-1]]+t[i-1][j];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[coins.length][amount];
    }
    public static boolean subset_sum(int[] arr,int n)
    {
        boolean[][] t=new boolean[arr.length+1][n+1];
        for(int i=0;i<arr.length+1;i++)
        {
            for(int j=0;j<n+1;j++)
            {
                if(j==0)
                    t[i][j]=true;
                else if(i==0)
                    t[i][j]=false;
                else if(arr[i-1]<=j)
                    t[i][j]=t[i-1][j-arr[i-1]] || t[i-1][j];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[arr.length][n];
    }
    public static boolean subset_halfsum(int[] arr)
    {
        int sum=0;
        for(int i=0;i<arr.length;i++)
            sum+=arr[i];
        boolean[][] t=new boolean[arr.length+1][sum+1];
        for(int i=0;i<arr.length+1;i++)
        {
            for(int j=0;j<sum+1;j++)
            {
                if(j==0)
                    t[i][j]=true;
                else if(i==0)
                    t[i][j]=false;
                else if(arr[i-1]<=j)
                    t[i][j]=t[i-1][j-arr[i-1]] || t[i-1][j];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[arr.length][sum/2];
    }
    public static int min_cost_matrix_chain_multiplication(int[][] t,int[] arr,int i,int j)
    {
        if(i>=j)
            return 0;
        if(t[i][j]!=0)
            return t[i][j];
        int min=Integer.MAX_VALUE;
        for(int k=i;k<j;k++)
        {
            int temp=min_cost_matrix_chain_multiplication(t,arr,i,k)+min_cost_matrix_chain_multiplication(t,arr,k+1,j)
                    +(arr[i-1]*arr[k]*arr[j]);
            if(temp<min)
                min=temp;
        }
        return t[i][j]=min;
    }
    public static int[][] floyd_warshall(int[][] arr)
    {
        int[][] t=graph();
        for(int k=0;k<arr.length;k++)
        {
            for(int i=0;i<arr.length;i++)
            {
                for(int j=0;j<arr.length;j++)
                {
                    t[i][j]=Math.min(t[i][j],t[i][k]+t[k][j]);
                }
            }
        }
        return t;
    }
    public static void print_graph(int[][] t)
    {
        for(int i=0;i<t.length;i++)
        {
            for(int j=0;j<t.length;j++)
            {
                if(t[i][j]<10)
                    System.out.print(t[i][j] + "    ");
                else
                    System.out.print(t[i][j] + "   ");
            }
            System.out.println();
        }
        return;
    }
    public static void insert_edge(int[][] t,int i,int j,int weight)
    {
        t[i][j]=weight;
    }
    public static int[][] graph()
    {
        int[][] t=new int[4][4];
        insert_edge(t,0,1,5);
        insert_edge(t,0,2,99);
        insert_edge(t,0,3,10);
        insert_edge(t,1,0,99);
        insert_edge(t,1,2,3);
        insert_edge(t,1,3,99);
        insert_edge(t,2,0,99);
        insert_edge(t,2,1,99);
        insert_edge(t,2,3,1);
        insert_edge(t,3,0,99);
        insert_edge(t,3,1,99);
        insert_edge(t,3,2,99);
        return t;
    }
    public static int optimal_game_strategy(int[] arr)
    {
        int[][] t=new int[arr.length][arr.length];
        int i=0,j=0;
        for(int k=0;k<arr.length;k++)
        {
            for(i=0,j=k;j<arr.length;i++,j++)
            {
                //i+2,j
                //i+1,j-1
                //i+1,j-1
                //i,j-2
                int x= ((i+2)<=j?t[i+2][j]:0);
                int y=((i+1)<=(j-1)?t[i+1][j-1]:0);
                int z=(i<=(j-2)?t[i][j-2]:0);
                t[i][j]=Math.max(arr[i]+Math.min(x,y),arr[j]+Math.min(y,z));
            }
        }
        return t[0][arr.length-1];
    }
    public static int longest_common_subsequence(String a,String b)
    {
        int[][] t=new int[a.length()+1][b.length()+1];
        for(int i=0;i<a.length()+1;i++)
        {
            for(int j=0;j<b.length()+1;j++)
            {
                if(i==0 || j==0)
                    t[i][j]=0;
                else if(a.charAt(i-1)==b.charAt(j-1))
                    t[i][j]=1+t[i-1][j-1];
                else
                    t[i][j]=Math.max(t[i-1][j],t[i][j-1]);
            }
        }
        return t[a.length()][b.length()];
    }
    public static String print_longest_common_subsequence(String a,String b)
    {
        int[][] t=new int[a.length()+1][b.length()+1];
        for(int i=0;i<a.length();i++)
        {
            for(int j=0;j<b.length();j++)
            {
                if(i==0 || j==0)
                    t[i][j]=0;
                else if(a.charAt(i-1)==b.charAt(j-1))
                    t[i][j]=1+t[i-1][j-1];
                else
                    t[i][j]=Math.max(t[i-1][j],t[i][j-1]);
            }
        }
        int i=a.length(),j=b.length();
        String str="";
        while(i!=0 && j!=0) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                str = a.charAt(i - 1) + str;
                i--;
                j--;
            }
            else if (t[i - 1][j] > t[i][j - 1])
                i--;
            else
                j--;
        }
        return str;
    }
    public static int edit_distance(String a,String b)
    {
        int[][] t=new int[a.length()+1][b.length()+1];
        for(int i=0;i<a.length()+1;i++)
        {
            for(int j=0;j<b.length()+1;j++)
            {
                if(i==0)
                    t[i][j]=j;
                else if(j==0)
                    t[i][j]=i;
                else if(a.charAt(i-1)==b.charAt(j-1))
                    t[i][j]=t[i-1][j-1];
                else
                    t[i][j]=1+Math.min(t[i-1][j],Math.min(t[i][j-1],t[i-1][j-1]));
            }
        }
        return t[a.length()][b.length()];
    }
    public static int longest_palindromic_subsequence(String a)
    {
        String str="";
        int i=0;
        while(i<a.length())
        {
            str=a.charAt(i)+str;
            i++;
        }
        return longest_common_subsequence(a,str);
    }
    public static String print_longest_palindromic_subsequence(String a)
    {
        String str="";
        int i=0;
        while(i<a.length())
        {
            str=a.charAt(i)+str;
            i++;
        }
        return print_longest_common_subsequence(a,str);
    }
    public static int longest_repeating_subsequence(String a)
    {
        int[][] t=new int[a.length()+1][a.length()+1];
        for(int i=0;i<a.length()+1;i++)
        {
            for(int j=0;j<a.length()+1;j++)
            {
                if(i==0 || j==0)
                    t[i][j]=0;
                else if(a.charAt(i-1)==a.charAt(j-1) && i!=j)
                    t[i][j]=1+t[i-1][j-1];
                else
                    t[i][j]=Math.max(t[i-1][j],t[i][j-1]);
            }
        }
        return t[a.length()][a.length()];
    }
    public static int number_of_times_repeating(String a,String b)
    {
        int[][] t=new int[a.length()+1][b.length()+1];
        for(int i=0;i<a.length()+1;i++)
        {
            for(int j=0;j<b.length()+1;j++)
            {
                if(i==0 && j!=0)
                    t[i][j]=0;
                else if(j==0)
                    t[i][j]=1;
                else if(a.charAt(i-1)==b.charAt(j-1))
                    t[i][j]=t[i-1][j-1]+t[i-1][j];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[a.length()][b.length()];
    }
    public static int max_apples(int[][] arr,int i,int j)
    {
        if(i>=arr.length || j>=arr.length)
            return 0;
        else
            return arr[i][j]+Math.max(max_apples(arr,i+1,j),max_apples(arr,i,j+1));
    }
    public static int max_apples_dynamic_programming(int[][] arr)
    {
        int[][] t=new int[arr.length][arr.length];
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                t[i][j]=arr[i][j];
                if(i==0 && j!=0)
                    t[i][j]+=t[i][j-1];
                else if(i!=0 && j==0)
                    t[i][j]+=t[i-1][j];
                else if(i>0 && j>0)
                    t[i][j]=t[i][j]+Math.max(t[i-1][j],t[i][j-1]);
            }
        }
        return t[arr.length-1][arr.length-1];
    }
    public static int[][] matrix()
    {
        int[][] matrix=new int[5][5];
        int k=10;
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                if(k!=0)
                {
                    matrix[i][j]=k;
                    k/=2;
                }
                else
                    k=20;
            }
        }
        return matrix;
    }
    public static void display_matrix(int[][] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                if(arr[i][j]<10)
                    System.out.print(arr[i][j] + "    ");
                else
                    System.out.print(arr[i][j] + "   ");
            }
            System.out.println();
        }
    }
    public static int max_size_square_sub_matrix(int[][] arr)
    {
        int[][] t=new int[arr.length][arr.length];
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                if(i==0 || j==0)
                    t[i][j]=arr[i][j];
                else if(arr[i][j]==1)
                    t[i][j]=1+Math.min(t[i][j-1],Math.min(t[i-1][j],t[i-1][j-1]));
                else
                    t[i][j]=0;
            }
        }
        int max=t[0][0];
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                if(t[i][j]>max)
                    max=t[i][j];
            }
        }
        return max;
    }
    public static int max_sum_sub_matrix(int[][] arr)
    {
        int[][] t=new int[arr.length][arr[0].length];
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                t[i][j]=arr[i][j];
                if(i==0 && j==0)
                    t[i][j]=arr[i][j];
                else if(i==0 && j!=0)
                    t[i][j]+=t[i][j-1];
                else if(i!=0 && j==0)
                    t[i][j]+=t[i-1][j];
                else
                    t[i][j]+=t[i-1][j]+t[i][j-1]-t[i-1][j-1];
                if(t[i][j]>max)
                    max=t[i][j];
            }
        }
        return max;
    }
    public static void main(String[] args)
    {
        int n=4;
        System.out.println("Recurrence of " + n + " is : " + recurrence_recursive(n));
        System.out.println("Recurrence of " + n + " through memoization is : " + recurrence_memoization(n));
        System.out.println("Recurrence of " + n + " through dp is : " + recurrence_dp(n));
        int[] arr={1,-3,4,-2,-1,6};
        System.out.println("Max sum efficient : " + max_sum(arr));
        System.out.println("Max sum in the array is : " + max_sum2(arr));
        System.out.println("Max sum with no two contagious is : " + max_sum_with_no_two_contagious(arr));
        System.out.println("Max sum with no three contagious is : " + max_with_no_three_contagious(arr));
        n=3;
        System.out.println("Number of BSTs that can be formed from " + n + " nodes are : " + catalan_number(n));
        System.out.println("Number of BSTs that can be formed from  " + n + " nodes through memoization are : " + catalan_number_memoization(n));
        int[] size={1,2,5,6,7};
        int[] values={4,2,8,9,10};
        System.out.println("Max value without without boundation is : " + unbounded_knapsack(size,values,10));
        System.out.println("Max value with boundation is : " + knapsack_01(size,values,10));
        int[] coins={1,2,4,5};
        System.out.println("Ways to make change of 10 is : " + coin_change(coins,7));
        n=20;
        values=new int[]{1,2,4,5,6};
        if(subset_sum(values,n))
            System.out.println("There exists a subset with sum " + n);
        else
            System.out.println("There does not exist a subset with sum " + n);
        if(subset_halfsum(values))
            System.out.println("There exists a subset with sum exactly half of sum of all the elements of the array.");
        else
            System.out.println("There does not exist a subset with sum exactly hald of the sum of all the elements of the array.");
        arr=new int[]{40,20,30,10,30};
        int[][] mcm_matrix=new int[arr.length][arr.length];
        System.out.println("Min cost of multiplying the matrices is : " + min_cost_matrix_chain_multiplication(mcm_matrix,arr,1,arr.length-1));
        int[][] t=graph();
        System.out.println("All pairs shortest path / Floyd Warshall Algorithm : ");
        System.out.println("Original graph :- ");
        print_graph(t);
        t=floyd_warshall(t);
        System.out.println("All pairs shortest path :- ");
        print_graph(t);
        arr=new int[]{20,30,2,2,2,10};
        System.out.println("Max money we can make through optimal strategy : " + optimal_game_strategy(arr));
        String a="acbeg";
        String b="abcd";
        System.out.println("Operations required to convert string " + a + " into string " + b + " is : " + edit_distance(a,b));
        a=new String("abdgh");
        b=new String("abgh");
        System.out.println("Length of common subsequence in string " + a + " and string " + b + " is : " + longest_common_subsequence(a,b));
        System.out.println("Longest common subsequence in string " + a + " and string " + b + " is : " + print_longest_common_subsequence(a,b));
        a=new String("acbdghdbca");
        System.out.println("Length of longest palindromic subsequence in string " + a + " is : " + longest_palindromic_subsequence(a));
        System.out.println("Longest palindromic subsequence in string " + a + " is : " + print_longest_palindromic_subsequence(a));
        a=new String("abcdmabcd");
        System.out.println("Length of longest repeating subsequence in string " + a + " is : " + longest_repeating_subsequence(a));
        a=new String("abadcb");
        b=new String("ab");
        System.out.println("Number of times string " + a + " is repeating in string " + b + " is : " + number_of_times_repeating(a,b));
        int[][] apples=matrix();
        System.out.println("Max apples that we can collect : ");
        System.out.println("Apples :- ");
        display_matrix(apples);
        System.out.println("Max apples : " + max_apples(apples,0,0));
        System.out.println("Max apples through dynamic programming : " + max_apples_dynamic_programming(apples));
        int[][] matrix={{0,1,1,0,1},{1,1,0,1,0},{0,1,1,1,0},{1,1,1,1,0},{1,1,1,1,1}};
        System.out.println("Maximum size square of a sub matrix with all 1s is : " + max_size_square_sub_matrix(matrix));
        matrix=new int[][]{{1,2,-1,-4,-20},{-8,-3,4,2,1},{3,8,10,1,3},{-4,-1,1,7,-6}};
        System.out.println("Maximum sum in sub matrix is : " + max_sum_sub_matrix(matrix));
    }
}
