
package lab0;


public class Sort {
    public int [] MergeSort(int []a, int lo, int hi){
        if(lo>hi){
            return null;
        }
        if(lo==hi){
            int []q=new int[1];
            q[0]=a[lo];
            return q;
        }
        int mid=(lo+hi)/2;
        int []b=MergeSort(a,lo,mid);
        int []c=MergeSort(a,mid+1,hi);
        a=Merge(b,c);
        return a;
    }
    public int [] Merge(int [] a, int [] b){
        int []z=new int[a.length+b.length];
        int i=0,j=0,k=0;
        for(;k<z.length;){
            if(i==a.length){
                z[k++]=b[j++];
            } else if(j==b.length){
                z[k++]=a[i++];
            }else {
            if(a[i]<b[j]){
               z[k++]=a[i++]; 
            }else {
                z[k++]=b[j++];
            }
            }
        }
        return z;
    }
    public int [] HeapSort(int [] a){
        
        for(int i=a.length/2;i>=0;i--){
            Heapify(a, i,a.length);
        }
        for(int i=a.length-1;i>=0;i--){
            int temp = a[0]; 
            a[0] = a[i]; 
            a[i] = temp; 
            Heapify(a,0,i);

        }
        return a;
    }
    
    public int [] Heapify(int [] a, int i, int num){
        
        int left=2*i;
        
        int right=2*i+1;
        int max=i;
        
        if(left<num && a[left]>a[max]){
            max=left;
            
        }
        if(right<num && a[right]>a[max]){
            max=right;
            
        } 
        if(max!=i){
            int temp=a[i];
            a[i]=a[max];
            a[max]=temp;

            Heapify(a,max,num);
            
        }
        return a;
    }
    
}
