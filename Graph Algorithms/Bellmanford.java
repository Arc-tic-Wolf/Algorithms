package Graph;
import java.io.File;
import java.util.Scanner;

public class Bellmanford{
    class Edge {
        int src, dest, weight;
        Edge()
        {
            src = dest = weight = 0;
        }
    };

    int V, E;
    Edge edge[];


    public Bellmanford(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    void BellmanFord(Bellmanford graph, int src)
    {
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];

        // Step 1: Initialize distances from src to all other
        // vertices as INFINITE
        for (int i = 0; i < V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;

        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int w = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
                    dist[v] = dist[u] + w;
            }
        }

        // Step 3: check for negative-weight cycles. The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        // path, then there is a cycle.
        for (int j = 0; j < E; ++j) {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int w = graph.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }
        print(dist, V);
    }


    void print(int dist[], int V)
    {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }

    public static void main(String args[]) throws Exception{

        int source;
        Scanner sc;
        try {

            File f = new File("F:\\Varsity\\src\\Graph\\bellmanford.txt");

            sc = new Scanner(f);

            int n = sc.nextInt();
            int e = sc.nextInt();



            Bellmanford t = new Bellmanford(n,e);
            for (int i = 0; i < e; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                t.edge[i].src=u;
                t.edge[i].dest=v;
                t.edge[i].weight=w;
            }
            int s=sc.nextInt();
            t.BellmanFord(t,s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}