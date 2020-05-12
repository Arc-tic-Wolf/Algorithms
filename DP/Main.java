package DP;

import java.util.Scanner;

public class Main {
    static int fms(int arr[], int n, int sum)
    {

        int curr_sum = arr[0], max_sum = 0, start = 0;

        for (int i = 1; i < n; i++) {


            if (curr_sum <= sum)
                max_sum = Math.max(max_sum, curr_sum);

            while (curr_sum + arr[i] > sum && start < i) {
                curr_sum -= arr[start];
                start++;
            }

            curr_sum += arr[i];
        }

        if (curr_sum <= sum)
            max_sum = Math.max(max_sum, curr_sum);

        return max_sum;
    }

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<a.length;i++){
            a[i]=sc.nextInt();
        }

        System.out.println(fms(a, n, m));
    }
}
