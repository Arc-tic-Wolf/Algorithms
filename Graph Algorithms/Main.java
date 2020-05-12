package Graph;

import java.io.File;
import java.util.*;

public class Main {



    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);

        try {

            File f = new File("graph.txt");

            scn = new Scanner(f);

            int n = scn.nextInt();
            int e = scn.nextInt();


            int[][] m = new int[n + 1][n + 1];
            int i = 0;
            LinkedList<Integer> l[]=new LinkedList[n+1];
            for(int k=0;k<=n;k++){
                l[k]=new LinkedList<>();
            }
            while (scn.hasNext()) {
                int u = scn.nextInt();
                int v = scn.nextInt();

                l[u].add(v);
                l[v].add(u);



                m[u][v] = 1;
                m[v][u] = 1;
                i++;

            }

            System.out.println("Matrix:");

            for(int k=1;k<m.length;k++){

                for(int j=1;j<m[k].length;j++){

                    System.out.print(m[k][j]+" ");

                }
                System.out.println();
            }

            System.out.println("List:");
            for(int k=0;k<n;k++){
                System.out.print(k+" -> ");
                for(Integer z:l[k]){
                    System.out.print(z+" ");
                }
                System.out.println();
            }

            Breadth b=new Breadth(n+1,m);
            b.bfs(m,n+1,0);
            System.out.println("Short path is : ");
            b.printPath(1,4);

            Depth d=new Depth(n+1,m);
            d.dfs(n+1);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
