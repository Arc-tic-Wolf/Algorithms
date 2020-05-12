package Graph;

public class Depth {
    int adj[][];
    int color[];
    int parent[];
    int dis[];
    int fin[];
    int size;
    int time=0;
    public Depth(int n,int[][]mat){
        size=n;
        adj=mat;
        color=new int[size];
        parent=new int[size];
        dis=new int[size];
        fin=new int[size];
    }
    void dfs(int vertex)
    {
        for(int i = 0 ; i < vertex ; i++)
            color[i] = 0; //white
        time = 0;
        System.out.println("DFS is : ");

        for(int i = 0 ; i<vertex ; i++)
        {
            if(color[i] == 0)
            {
                dfsVisit(i , vertex);
            }
        }
    }

    void dfsVisit(int u , int vertex)
    {
        color[u] = 1;
        dis[u] = time++;
        for(int i = 0 ; i<vertex ; i++)
        {
            if(adj[u][i] == 1)
            {
                int v = i;
                if(color[v] == 0)
                {
                    parent[v] = u;
                    dfsVisit(v , vertex);
                }
            }
        }
        color[u] = 2;
        fin[u] = time++;
        System.out.print(u+" ");


    }
}
