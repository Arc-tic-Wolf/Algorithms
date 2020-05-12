package Graph;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Dijkstra {
    static int sum=0;

    public Dijkstra(int n){
        V=n;
    }
    public int V;
    int minKey(int dis[], Boolean visited[])
    {

        int min = Integer.MAX_VALUE, index = -1;

        for (int v = 0; v < V; v++)
            if (visited[v] == false && dis[v] <= min) {
                min = dis[v];
                index = v;
            }

        return index;
    }


    void print(int dis[])
    {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dis[i]);
    }

    void dijkstra(int graph[][], int src)
    {
        int dis[] = new int[V];
        Boolean visited[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dis[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dis[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minKey(dis, visited);

            visited[u] = true;

            for (int v = 0; v < V; v++) {


                if (!visited[v] && graph[u][v] != 0 && dis[u] != Integer.MAX_VALUE && dis[u] + graph[u][v] < dis[v]) {
                    dis[v] = dis[u] + graph[u][v];
                }
            }
        }
        for (int i = 0; i < V; i++)
            sum+=dis[i];

    }


    public static void main(String[] args) throws Exception{
        Scanner sc;
        try {

            File f = new File("F:\\Varsity\\src\\Graph\\dijkstra.txt");

            sc = new Scanner(f);

            int n = sc.nextInt();
            int e = sc.nextInt();


            int[][] graph = new int[n][n];
            Dijkstra t = new Dijkstra(n);
            for(int i=0;i<e;i++){
                int u=sc.nextInt();
                int v=sc.nextInt();
                int w=sc.nextInt();
                graph[u][v]=w;
                graph[v][u]=w;
            }
            for (int i = 0; i < t.V; i++)
                 t.dijkstra(graph, i);
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}