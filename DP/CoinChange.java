package DP;


import java.util.Scanner;

public class CoinChange {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<a.length;i++){
            a[i]=sc.nextInt();
        }
        System.out.println(calc(a, n, m));
    }
    public static int calc( int S[], int m, int n )
    {

        int max=0;
        int table[]=new int[n+1];

        table[0] = 1;
        int j=0;
        for(int i=0; i<m; i++)
            for(j=S[i]; j<=n; j++) {
                table[j] += table[j - S[i]];
            }

        return j-1;
    }



}
