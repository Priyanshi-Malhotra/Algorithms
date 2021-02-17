import java.util.*;

public class Graphs {
    static class ListNode {
        int v,weight;
        public ListNode(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
        public int getV() {
            return v;
        }
        public int getWeight() {
            return weight;
        }
    }
    public static void addEdge(ArrayList<ArrayList<Integer>> adj,int u,int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    public static void bfs(ArrayList<ArrayList<Integer>> adj){
        boolean[] visited=new boolean[6];
        Queue<Integer> queue=new LinkedList<>();
        visited[0]=true;
        queue.offer(0);

        while(!(queue.isEmpty())){
            int j=queue.poll();
            System.out.print(j + " ");
            Iterator<Integer> i=adj.get(j).listIterator();
            while(i.hasNext()){
                int n=i.next();
                if(!(visited[n])){
                    visited[n]=true;
                    queue.offer(n);
                }
            }
        }
        System.out.println();
    }
    public static void dfs_recursive(ArrayList<ArrayList<Integer>> adj,int u,boolean[] visited){
        visited[u]=true;
        System.out.print(u + " ");
        Iterator<Integer> i=adj.get(u).listIterator();
        while(i.hasNext()){
            int n=i.next();
            if(!(visited[n]))
                dfs_recursive(adj,n,visited);
        }
    }
    public static void dfs(ArrayList<ArrayList<Integer>> adj){
        boolean[] visited=new boolean[6];
        Stack<Integer> stack=new Stack<>();
        visited[0]=true;
        stack.push(0);
        System.out.print("0 ");

        while(!stack.isEmpty()){
            int a=stack.peek();
            Iterator<Integer> i=adj.get(a).listIterator();
            int l=0;
            while(i.hasNext() && l==0){
                int n=i.next();
                if(!(visited[n])){
                    visited[n]=true;
                    stack.push(n);
                    l=1;
                    System.out.print(n + " ");
                    break;
                }
            }
            if(!(stack.isEmpty()) && l==0)
                stack.pop();
        }
        System.out.println();
    }
    public static void shortestPathInUnweightedGraph(ArrayList<ArrayList<Integer>> adj){
        int[] distance=new int[6];
        distance[0]=0;

        boolean[] visited=new boolean[6];
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(0);
        visited[0]=true;

        while(!(queue.isEmpty())){
            int a=queue.poll();

            Iterator<Integer> i=adj.get(a).listIterator();
            while(i.hasNext()){
                int n=i.next();
                if(!(visited[n])){
                    visited[n]=true;
                    distance[n]=distance[a]+1;
                    queue.offer(n);
                }
            }
        }

        for(Integer i:distance)
            System.out.print(i + " ");
        System.out.println();
    }
    public static boolean checkCycle(ArrayList<ArrayList<Integer>> adj,int s,boolean[] visited,int parent){
        visited[s]=true;

        Iterator<Integer> i=adj.get(s).listIterator();
        while(i.hasNext()){
            int n=i.next();
            if(!visited[n]) {
                if (checkCycle(adj, n, visited, s))
                    return true;
            }
            else if(n!=parent)
                return true;
        }
        return false;
    }
    public static boolean checkCycleUndirectedGraph(ArrayList<ArrayList<Integer>> adj){
        boolean[] visited=new boolean[6];

        for(int i=0;i<adj.size();i++)
            if(!visited[i])
                if(checkCycle(adj,i,visited,-1))
                    return true;
        return false;
    }
    public static boolean checkCycle(ArrayList<ArrayList<Integer>> adj,int s,boolean[] visited,boolean[] recVisited){
        visited[s]=true;
        recVisited[s]=true;

        Iterator<Integer> i=adj.get(s).listIterator();
        while(i.hasNext()){
            int n=i.next();
            if(!visited[n] && checkCycle(adj,n,visited,recVisited))
                return true;
            else if(recVisited[n])
                return true;
        }
        recVisited[s]=false;
        return false;
    }
    public static boolean checkCycleDirectedGraph(ArrayList<ArrayList<Integer>> adj){
        boolean[] visited=new boolean[6];
        boolean[] recVisited=new boolean[6];
        for(int i=0;i<adj.size();i++){
            if(!visited[i])
                if(checkCycle(adj,i,visited,recVisited))
                    return true;
        }
        return false;
    }
    public static void addEdge(ArrayList<ArrayList<Integer>> adj,int u,int v,int[] indegree){
        adj.get(u).add(v);
        indegree[v]++;
    }
    public static void topologicalSortBFS(ArrayList<ArrayList<Integer>> adj,int[] indegree){//Kahn's Algorithm
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<indegree.length;i++)
            if(indegree[i]==0)
                queue.offer(i);
        while(!(queue.isEmpty())){
            int n=queue.poll();
            System.out.print(n + " ");

            for(Integer i:adj.get(n)){
                indegree[i]--;
                if(indegree[i]==0)
                    queue.offer(i);
            }
        }
        System.out.println();
    }
    public static boolean checkCycleUsingTopologicalSort(ArrayList<ArrayList<Integer>> adj,int[] indegree){
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<indegree.length;i++)
            if(indegree[i]==0)
                queue.offer(i);
        int count=0;
        while(!queue.isEmpty()){
            int a=queue.poll();
            count++;
            for(Integer n:adj.get(a)){
                indegree[n]--;
                if(indegree[n]==0)
                    queue.offer(n);
            }
        }
        return count!=indegree.length;
    }
    public static void topologicalDFS(ArrayList<ArrayList<Integer>> adj,Stack<Integer> stack,boolean[] visited,int s){
        visited[s]=true;
        for(Integer i:adj.get(s))
            if(!visited[i])
                topologicalDFS(adj,stack,visited,i);
        stack.push(s);
    }
    public static void topologicalSortDFS(ArrayList<ArrayList<Integer>> adj){
        Stack<Integer> stack=new Stack<>();
        boolean[] visited=new boolean[6];
        for(int i=0;i<adj.size();i++)
            if(!visited[i])
                topologicalDFS(adj,stack,visited,i);
        while(!stack.isEmpty())
            System.out.print(stack.pop() + " ");
        System.out.println();
    }
    public static void addEdge(ArrayList<ArrayList<ListNode>> adj,int u,int v,int weight,int[] indegree){
        ListNode listNode=new ListNode(v,weight);
        indegree[v]++;
        adj.get(u).add(listNode);
    }
    public static ArrayList<Integer> topologicalSortBFSShortestPath(ArrayList<ArrayList<ListNode>> adj,int[] indegree){
        ArrayList<Integer> list=new ArrayList<>();
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(0);
        indegree[0]--;
        while(!(queue.isEmpty())){
            int a=queue.poll();
            list.add(a);
            for(ListNode i:adj.get(a)){
                int n=i.getV();
                indegree[n]--;
                if(indegree[n]==0)
                    queue.offer(n);
            }
        }
        return list;
    }
    public static void shortestPathInWeightedGraph(ArrayList<ArrayList<ListNode>> adj,ArrayList<Integer> list){
        int[] shortestPath=new int[6];
        for(int i=0;i<6;i++)
            shortestPath[i]=Integer.MAX_VALUE;
        shortestPath[0]=0;
        for(Integer a:list){
            for(ListNode i:adj.get(a)){
                int n=i.getV(),weight=i.getWeight();
                if(shortestPath[n]>shortestPath[a]+weight)
                    shortestPath[n]=shortestPath[a]+weight;
            }
        }
        for(Integer i:shortestPath)
            System.out.print(i + " ");
        System.out.println();
    }
    public static void primsAlgorithm(int[][] graph){//Minimum Spanning Tree
        boolean[] mstSet=new boolean[graph.length];
        int[] key=new int[graph.length];
        Arrays.fill(key,Integer.MAX_VALUE);
        key[0]=0;
        int totalWeight=0;
        for(int count=0;count<graph.length;count++){
            int u=-1;
            for(int i=0;i<key.length;i++)
                if(!mstSet[i] && (u==-1 || key[i]<key[u]))
                    u=i;
            mstSet[u]=true;
            totalWeight+=key[u];
            for(int j=0;j<key.length;j++)
                if(!mstSet[j] && graph[u][j]!=0 && graph[u][j]<key[j])
                    key[j]=graph[u][j];
        }
        System.out.println(totalWeight);
    }
    public static void dijkstrasAlgorithm(int[][] graph){
        int[] min=new int[graph.length];
        Arrays.fill(min,Integer.MAX_VALUE);
        min[0]=0;
        boolean[] mstSet=new boolean[graph.length];
        for(int count=0;count<graph.length;count++){
            int u=-1;
            for(int i=0;i<graph.length;i++)
                if(!mstSet[i] && (u==-1 || min[i]<min[u]))
                    u=i;
            mstSet[u]=true;
            for(int i=0;i<graph.length;i++)
                if(!mstSet[i] && graph[u][i]!=0 && min[i]>min[u]+graph[u][i])
                    min[i]=min[u] + graph[u][i];
        }
        for(int i:min)
            System.out.print(i + " ");
        System.out.println();
    }
    //Kosaraju's Algorithm // strongly connected graphs
    public static Stack<Integer> topologicalKosarajuDFSRecursive(ArrayList<ArrayList<Integer>> adj,int u,Stack<Integer> stack,boolean[] visited){
        visited[u]=true;
        for(int i:adj.get(u))
            if(!visited[u])
                topologicalKosarajuDFSRecursive(adj,i,stack,visited);
        stack.push(u);
        return stack;
    }
    public static Stack<Integer> topologicalKosarajuDFS(ArrayList<ArrayList<Integer>> adj){
        Stack<Integer> stack=new Stack<>();
        boolean[] visited=new boolean[adj.size()];
        for(int i=0;i<adj.size();i++)
            if(!visited[i])
                stack=topologicalKosarajuDFSRecursive(adj,i,stack,visited);
        return stack;
    }
    public static void transposeEdge(ArrayList<ArrayList<Integer>> adj,int u,int v){
        adj.get(v).add(u);
    }
    public static ArrayList<ArrayList<Integer>> getTranspose(ArrayList<ArrayList<Integer>> adj){
        ArrayList<ArrayList<Integer>> list=new ArrayList<>(adj.size());
        for(int i=0;i<adj.size();i++)
            list.add(new ArrayList<>());
        for(int i=0;i<adj.size();i++)
            for(int j:adj.get(i))
                transposeEdge(list,i,j);
         return list;
    }
    public static void kosarajuDFSPrint(ArrayList<ArrayList<Integer>> adj,int u,boolean[] visited){
        visited[u]=true;
        System.out.print(u + " ");
        for(int i:adj.get(u))
            if(!visited[i])
                kosarajuDFSPrint(adj,i,visited);
    }
    public static void kosarajusAlgorithm(ArrayList<ArrayList<Integer>> adj){
        Stack<Integer> stack=topologicalKosarajuDFS(adj);
        adj=getTranspose(adj);
        boolean[] visited=new boolean[adj.size()];
        while(!stack.isEmpty()){
            int u=stack.pop();
            for(int i:adj.get(u))
                if(!visited[i]) {
                    kosarajuDFSPrint(adj, i, visited);
                    System.out.println();
                }
        }
    }
    //Articulation Point
    static int time=0;
    public static void articulationRecursive(ArrayList<ArrayList<Integer>> adj,int u,boolean[] visited,int[] discoveryTime,int[] low,int[] parent
            ,boolean[] ap){
        visited[u]=true;
        int children=0;
        discoveryTime[u]=low[u]=++time;
        for(int i:adj.get(u)){
            if(!visited[i]){
                children++;
                parent[i]=u;
                articulationRecursive(adj,i,visited,discoveryTime,low,parent,ap);

                low[u]=Math.min(low[u],low[i]);
                if(parent[u]==-1 && children>1)
                    ap[u]=true;
                if(parent[u]!=-1 && low[i]>=discoveryTime[u])
                    ap[u]=true;
            }
            else if(i!=parent[u])
                low[u]=Math.min(low[u],discoveryTime[i]);
        }
    }
    public static void articulationPoint(ArrayList<ArrayList<Integer>> adj){
        boolean[] visited=new boolean[adj.size()];
        int[] discoveryTime=new int[adj.size()];
        int[] low=new int[adj.size()];
        int[] parent=new int[adj.size()];
        boolean[] ap=new boolean[adj.size()];
        Arrays.fill(parent,-1);

        for(int i=0;i<adj.size();i++)
            if(!visited[i])
                articulationRecursive(adj,i,visited,discoveryTime,low,parent,ap);
        for(int i=0;i<ap.length;i++)
            if(ap[i])
                System.out.print(i + " ");
        System.out.println();
    }
    public static void main(String[] args){
        int n=6;
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>(n);
        for(int i=0;i<6;i++)
            adj.add(new ArrayList<>());
        //BFS
        addEdge(adj,0,1);
        addEdge(adj,0,2);
        addEdge(adj,0,3);
        addEdge(adj,2,4);
        //addEdge(adj,4,2);
        //DFS
        /*addEdge(adj,0,1);
        addEdge(adj,0,2);
        addEdge(adj,0,4);
        addEdge(adj,4,3);*/
        boolean[] visited=new boolean[6];
        System.out.println("DFS traversal of the graph : ");
        dfs(adj);
        System.out.println("DFS recursive approach : ");
        dfs_recursive(adj,0,visited);
        System.out.println();
        System.out.println("BFS traversal of the graph : ");
        bfs(adj);
        System.out.println("Shortest path in an unweighted graph : ");
        shortestPathInUnweightedGraph(adj);
        if(checkCycleUndirectedGraph(adj))
            System.out.println("Cycle Exists.");
        else
            System.out.println("Cycle does not exist.");
        if(checkCycleDirectedGraph(adj))
            System.out.println("Cycle Exists.");
        else
            System.out.println("Cycle does not exist.");
        int[] indegree=new int[6];
        adj= new ArrayList<>();
        for(int i=0;i<6;i++)
            adj.add(new ArrayList<Integer>());
        addEdge(adj,0,2,indegree);
        addEdge(adj,0,3,indegree);
        addEdge(adj,1,3,indegree);
        addEdge(adj,1,4,indegree);
        addEdge(adj,4,5,indegree);
        //addEdge(adj,5,5,indegree);
        System.out.println("Topological sort BFS : ");
        topologicalSortBFS(adj,indegree);
        /*if(checkCycleUsingTopologicalSort(adj,indegree))
            System.out.println("Cycle Exists.");
        else
            System.out.println("Cycle does not exist.");*/
        System.out.println("Topological sort DFS : ");
        topologicalSortDFS(adj);
        ArrayList<ArrayList<ListNode>> adjList=new ArrayList<>();
        for(int i=0;i<6;i++)
            adjList.add(new ArrayList<>());
        addEdge(adjList,0,1,2,indegree);
        addEdge(adjList,0,4,1,indegree);
        addEdge(adjList,1,2,3,indegree);
        addEdge(adjList,4,2,2,indegree);
        addEdge(adjList,4,5,4,indegree);
        addEdge(adjList,2,3,6,indegree);
        addEdge(adjList,5,3,1,indegree);
        System.out.println("Shortest path : ");
        ArrayList<Integer> list=topologicalSortBFSShortestPath(adjList,indegree);
        shortestPathInWeightedGraph(adjList,list);
        int[][] graph ={ { 0, 5, 8, 0},
                { 5, 0, 10, 15 },
                { 8, 10, 0, 20 },
                { 0, 15, 20, 0 }};
        System.out.println("Prim's Algorithm : ");
        primsAlgorithm(graph);
        graph =new int[][]{ { 0, 50, 100, 0},
                { 50, 0, 30, 200 },
                { 100, 30, 0, 20 },
                { 0, 200, 20, 0 },};
        System.out.println("Dijkstra's Algorithm : ");
        dijkstrasAlgorithm(graph);
        /*adj=new ArrayList<>(4);
        for(int i=0;i<4;i++)
            adj.add(new ArrayList<>());
        addEdge(adj,0,1);
        addEdge(adj,1,2);
        addEdge(adj,1,3);*/
        adj=new ArrayList<>(6);
        for(int i=0;i<6;i++)
            adj.add(new ArrayList<>());
        addEdge(adj,0,2);
        addEdge(adj,0,3);
        addEdge(adj,1,3);
        addEdge(adj,1,4);
        addEdge(adj,4,5);
        System.out.println("Kosaraju's Algorithm : ");
        kosarajusAlgorithm(adj);
        System.out.println("Articulation Points : ");
        articulationPoint(adj);
    }
}