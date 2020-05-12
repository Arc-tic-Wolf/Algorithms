package Graph;
import java.util.*;
import java.io.*;
public class Breadth {

    // 0 is white, 1 is gray , 2 is black
    int adj [][];
    int color[];
    int parent[];
    int dis[];
    int size;
    public Breadth(int n,int [][]mat){
        size=n;
        adj=mat;
        color=new int[size];
        parent=new int[size];
        dis=new int[size];
    }
    public void bfs(int adj[][],int vertex,int start){
        for(int i=0;i<size;i++){
            if(i!=start){
                color[i]=0; //white
                dis[i]= Integer.MAX_VALUE; //infinity
                parent[i]=-1;  //null

            }
        }
        color[start]=1; //gray
        dis[start]=0;
        parent[start]=-1; //null
        Queue <Integer> bfsQueue=new LinkedList<>();
        bfsQueue.add(start);
        System.out.println("BFS is : ");

        while(!bfsQueue.isEmpty())
        {
            int u = bfsQueue.remove();
            System.out.print(u+" ");


            for(int i = 0 ; i < vertex ; i++)
            {
                if(adj[u][i]!=0)
                {
                    int v = i;
                    if(color[v] == 0) //white
                    {
                        color[v] = 1; //gray
                        dis[v] = dis[u]+1;
                        parent[v] = u;
                        //System.out.println("Parent of "+v+" is "+parent[v]);
                        bfsQueue.add(v);
                    }
                }
            }
            color[u] = 2; //black
        }

    }
    public void printPath(int start , int end)
    {

        if(start==end){
            System.out.print(start+" ");
            return;
        }else if(parent[end]==-1){
            return;
        }else {
            printPath(start, parent[end]);
        }
            System.out.println(end);

        //strrev(path);


    }

}
