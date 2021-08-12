sum=["Leaf","Sand","Cloud","Mist","Sound","Rock"] 
class Graph():
  
    def __init__(self, vertices):
        self.V = vertices
        self.graph = [[0 for column in range(vertices)] 
                    for row in range(vertices)]
  
    def printSolution(self, dist):
        print("Vertex \tDistance from Source")
        for node in range(self.V):
            print(sum[node], "\t", dist[node])
  
    # A utility function to find the vertex with 
    # minimum distance value, from the set of vertices 
    # not yet included in shortest path tree
    def minDistance(self, dist, sptSet):
  
        # Initilaize minimum distance for next node
        min = 9999
  
        # Search not nearest vertex not in the 
        # shortest path tree
        for v in range(self.V):
            if dist[v] < min and sptSet[v] == False:
                min = dist[v]
                min_index = v
  
        return min_index
  
    # Funtion that implements Dijkstra's single source 
    # shortest path algorithm for a graph represented 
    # using adjacency matrix representation
    def dijkstra(self, src):
  
        dist = [9999] * self.V
        dist[src] = 0
        sptSet = [False] * self.V
  
        for cout in range(self.V):
  
            # Pick the minimum distance vertex from 
            # the set of vertices not yet processed. 
            # u is always equal to src in first iteration
            u = self.minDistance(dist, sptSet)
  
            # Put the minimum distance vertex in the 
            # shotest path tree
            sptSet[u] = True
  
            # Update dist value of the adjacent vertices 
            # of the picked vertex only if the current 
            # distance is greater than new distance and
            # the vertex in not in the shotest path tree
            for v in range(self.V):
                if self.graph[u][v] > 0 and sptSet[v] == False and \
                dist[v] > dist[u] + self.graph[u][v]:
                        dist[v] = dist[u] + self.graph[u][v]
  
        self.printSolution(dist)
  
# Driver program
g = Graph(6)
#           L   Sa  C   M   So  R 
g.graph =  [[0,238,253,239,272,305], #L
            [238,0,222,208,239,1163], #Sa
            [253,222,0,228,536,296], #C
            [239,208,228,0,212,230], #M
            [272,239,536,212,0,1101], #So
            [305,1163,296,230,1101,0], #R
        ];
i=0
while i<6:
    print("Source is "+sum[i])
    g.dijkstra(i)
    i+=1
