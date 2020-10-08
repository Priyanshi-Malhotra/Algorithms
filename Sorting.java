public class Sorting {
    public static void bubble_sort(int[] arr)
    {
        int flag=1;
        for(int i = 0;i<arr.length && flag==1; i++)
        {
            flag=0;
            for(int j=0;j<arr.length-1;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    flag=1;
                }
            }
        }
        return;
    }
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
    public static void insertion_sort(int[] arr)
    {
        for(int i=1;i<arr.length;i++)
        {
            int temp=arr[i],j=i;
            while(j>0 && arr[j-1]>temp)
            {
                arr[j]=arr[j-1];
                j--;
            }
            arr[j]=temp;
        }
        return;
    }
    public static void shell_sort(int[] arr)
    {
        for(int h=arr.length/2;h>0;h/=2)
        {
            for(int i=h;i<arr.length;i=i+h)
            {
                int j=i;
                while(j-h>=0 && arr[j-h]>
                        arr[j])
                {
                    int temp=arr[j-h];
                    arr[j-h]=arr[j];
                    arr[j]=temp;
                    j-=h;
                }
            }
        }
        return;
    }
    public static void merge_sort(int[] arr,int left,int right)
    {
        int mid=0;
        if(left<right)
        {
            mid=(left+right)/2;
            merge_sort(arr,left,mid);
            merge_sort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }
    public static void merge(int[] arr,int left,int mid,int right)
    {
        int[] temp=new int[arr.length];
        int i=left,j=mid,pos=left;
        while(i<mid && j<right)
        {
            if(arr[i]<arr[j])
                temp[pos++]=arr[i++];
            else
              temp[pos++]=arr[j++];
        }
        while(i<mid)
        {
            temp[pos++]=arr[i++];
        }
        while(j<right)
        {
            temp[pos++]=arr[j++];
        }
        for(i=left;i<right;i++)
            arr[i] = temp[i];
        return;
    }
    public static void quick_sort(int[] arr,int left,int right)
    {
        if(left<right)
        {
            int pivot=quick_sort_algo(arr,left,right);
            quick_sort(arr,left,pivot-1);
            quick_sort(arr,pivot+1,right);
        }
        return;
    }
    public static int quick_sort_algo(int[] arr,int left,int right)
    {
        int i=left,j=left,pivot=arr[right];
        while(j<right)
        {
            if(arr[j]<pivot)
            {
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
                i++;
            }
            j++;
        }
        int temp=arr[i];
        arr[i]=arr[right];
        arr[right]=temp;
        return i;
    }
    public static int max_element(int[] arr)
    {
        int max=arr[0];
        for(int i=1;i<arr.length;i++)
            if(arr[i]>max)
                max=arr[i];
        return max;
    }
    public static void count_sort(int[] arr)
    {
        int max=max_element(arr);
        max++;
        int[] count=new int[max];
        for(int i=0;i<arr.length;i++)
            count[arr[i]]++;
        for(int i=1;i<max;i++)
            count[i]=count[i]+count[i-1];
        int[] output=new int[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            output[count[arr[i]]-1]=arr[i];
            count[arr[i]]--;
        }
        for(int i=0;i<arr.length;i++)
            arr[i]=output[i];
        return;
    }
    /*public static void count_sort(int[] arr)
    {
        int max=max_element(arr);
        int[] count=new int[max];
        for(int i=0;i<arr.length;i++)
            count[arr[i]-1]++;
        for(int i=1;i<max;i++)
            count[i]=count[i]+count[i-1];
        int[] output=new int[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            output[count[arr[i]-1]-1]=arr[i];
            count[arr[i]-1]--;
        }
        for(int i=0;i<arr.length;i++)
            arr[i]=output[i];
        return;
    }*/
    public static void radix_sort(int[] arr)
    {
        int max=max_element(arr);
        for(int n=1;max/n>0;n*=10)
            count_sort(arr);
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
    public static void main(String[] args)
    {
        int[] arr=new int[10];
        arr(arr);
        bubble_sort(arr);
        display(arr);
        arr(arr);
        selection_sort(arr);
        display(arr);
        arr(arr);
        insertion_sort(arr);
        display(arr);
        arr(arr);
        shell_sort(arr);
        display(arr);
        arr(arr);
        merge_sort(arr,0,arr.length);
        display(arr);
        arr(arr);
        quick_sort(arr,0,arr.length-1);
        display(arr);
        arr(arr);
        count_sort(arr);
        display(arr);
        arr(arr);
        radix_sort(arr);
        display(arr);
    }
}
