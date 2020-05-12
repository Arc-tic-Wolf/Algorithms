package DP;

import java.util.Scanner;

public class Knapsack {



        static int max(int a, int b) { return (a > b)? a : b; }


        static int knapSack(int W, int wt[], int val[], int n)
        {
            int i, w;
            int K[][] = new int[n+1][W+1];


            for (i = 0; i <= n; i++)
            {
                for (w = 0; w <= W; w++)
                {
                    if (i==0 || w==0)
                        K[i][w] = 0;
                    else if (wt[i-1] <= w) {
                        if(val[i - 1] + K[i - 1][w - wt[i - 1]]<=W ){
                            K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);

                        }

                    }
                    else
                        K[i][w] = K[i-1][w];
                }
            }

            return K[n][W];
        }



        public static void main(String args[])
        {
            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt();
            int m=sc.nextInt();
            int a[] = new int[n];
            int b[]=new int[n];
            for(int i=0;i<a.length;i++){
                a[i]=sc.nextInt();
                b[i]=a[i];
            }

            System.out.println(knapSack(m, b, a, n));
        }

}
