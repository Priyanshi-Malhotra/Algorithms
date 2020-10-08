public class Searching {
    public static void selection_sort(int[] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            int min=i;
            for(int j=i+1;j<arr.length;j++)
                if(arr[j]<arr[min])
                    min=j;
            int temp=arr[min];
            arr[min]=arr[i];
            arr[i]=temp;
        }
        return;
    }
    public static int binary_search_without_recursion(int[] arr,int data)
    {
        int left=0,right=arr.length-1;
        while(left<=right)
        {
            int mid=(right+left)/2;
            if(arr[mid]==data)
                return mid;
            else if(arr[mid]<data)
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
    }
    public static int binary_search_recursion(int[] arr,int left,int right,int data)
    {
        if(left>right)
            return -1;
        int mid=(right+left)/2;
        if(arr[mid]==data)
            return mid;
        return (arr[mid]<data?binary_search_recursion(arr,mid+1,right,data):binary_search_recursion(arr,left,mid-1,data));
    }
    public static int interpolation_search(int[] arr,int data)
    {
        int left=0,right=arr.length-1;
        while(arr[left]<=data && arr[right]>=data)
        {
            if(arr[right]-arr[left]==0)
                return (right+left)/2;
            int mid=left+((data-arr[left]) * (right-left)/arr[right]-arr[left]);
            if(arr[mid]==data)
                return mid;
            else if(arr[mid]<data)
                left=mid+1;
            else
                right=mid-1;
        }
        if(arr[left]==data)
            return left;
        return -1;
    }
    public static void display(int[] arr)
    {
        for(int i=0;i<arr.length;i++)
            System.out.print(arr[i] + " ");
        System.out.println();
        return;
    }
    public static void arr(int[] arr)
    {
        arr[0]=10;
        arr[1]=5;
        arr[2]=9;
        arr[3]=2;
        arr[4]=7;
        arr[5]=4;
        arr[6]=8;
        arr[7]=1;
        arr[8]=6;
        arr[9]=3;
        display(arr);
        return;
    }
    public static void main(String[] args) {
        int[] arr = new int[10];
        arr(arr);
        selection_sort(arr);
        display(arr);
        System.out.println(binary_search_without_recursion(arr,4));
        System.out.println(binary_search_recursion(arr,0,arr.length-1,4));
        System.out.println(interpolation_search(arr,4));
    }
}
