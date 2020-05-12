package bfs;

import java.util.*;

public class Main {
    public static boolean BFS(LinkedList<Integer> al[], int src, int dest, int v, int pred[], int dist[]){
        Queue<Integer> q = new LinkedList<>();
        boolean visited[]=new boolean[v];
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
        visited[src] = true;
        dist[src] = 0;
        q.add(src);
        for(;q.size()!=0;) {
            int u = q.peek();
            q.poll();
            for (int i = 0; i < al[u].size(); i++) {
                if (visited[al[u].get(i)] == false) {
                    visited[al[u].get(i)] = true;
                    dist[al[u].get(i)] = dist[u] + 1;
                    pred[al[u].get(i)] = u;
                    q.add(al[u].get(i));

                    if (al[u].get(i) == dest)
                        return true;
                }
            }
        }

        return false;
    }
    public static int shortPath(LinkedList <Integer>adj[], int s, int t, int n){
        int pred[]=new int[n];
        int dist[]=new int[n];
        if (BFS(adj, s, t, n, pred, dist) == false){
            System.out.println("Disconnected");
            return -1;
        }
        LinkedList<Integer>path=new LinkedList<>();
        int e = t;
        path.add(e);
        for(;pred[e] != -1;) {
            path.add(pred[e]);
            e = pred[e];
        }
        return dist[t];


    }
    public static void main (String [] args){
        Scanner sc=new Scanner(System.in);
        int n,e,s,t;
        n=sc.nextInt();
        e=sc.nextInt();
        s=sc.nextInt();
        t=sc.nextInt();
        LinkedList<Integer> al[]=new LinkedList[n];
        for(int i=0;i<al.length;i++){
            al[i]=new LinkedList<>();
        }
        for(int i=0;i<e;i++){
            int v1=sc.nextInt();
            int v2=sc.nextInt();
            al[v1].add(v2);
            al[v2].add(v1);

        }

        System.out.print("The distance between starting node and target node:"+shortPath(al, s, t, n));


    }

}

