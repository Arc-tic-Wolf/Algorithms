package Graph;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Prims {
    public Prims(int n){
        V=n;
    }
    public int V;


    int minKey(int cost[], Boolean visited[])
    {

        int min = Integer.MAX_VALUE, index = -1;

        for (int v = 0; v < V; v++)
            if (visited[v] == false && cost[v] < min) {
                min = cost[v];
                index = v;
            }

        return index;
    }

    public static int sum=0;
    void print(int parent[], int graph[][])
    {

        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            sum+=graph[i][parent[i]];
        }

    }

    void prim(int graph[][])
    {

        int parent[] = new int[V];

        int cost[] = new int[V];

        Boolean visited[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            cost[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }


        cost[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < V - 1; count++) {

            int u = minKey(cost, visited);
            visited[u]=true;

            for (int v = 0; v < V; v++)

                if (graph[u][v] != 0 && visited[v] == false && graph[u][v] < cost[v]) {
                    parent[v] = u;
                    cost[v] = graph[u][v];
                }
        }


        print(parent, graph);
    }

    public static void main(String[] args) throws Exception{

        Scanner sc;
        try {

            File f = new File("F:\\Varsity\\src\\Graph\\prims.txt");

            sc = new Scanner(f);

            int n = sc.nextInt();
            int e = sc.nextInt();


            int[][] graph = new int[n][n];
            Prims t = new Prims(n);
            for(int i=0;i<e;i++){
                int u=sc.nextInt();
                int v=sc.nextInt();
                int w=sc.nextInt();
                graph[u][v]=w;
                graph[v][u]=w;
            }





            t.prim(graph);
            System.out.println("minimum cost is "+sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}