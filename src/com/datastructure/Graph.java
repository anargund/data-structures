package com.datastructure;

import java.util.*;

public class Graph {
    private List<Edge>[] node;
    private int verrtexNo;

    public Graph(int n) {
        verrtexNo = n;
        node = new LinkedList[n];
        for (int i = 0; i < node.length; i++) {
            node[i] = new LinkedList<>();
        }
    }

    private void addEdge(int node1, int node2, int weight) {
        node[node1].add(0, new Edge(node2, weight));
    }

    public boolean isConnected(int node1, int node2) {
        for(Edge edge : node[node1]) {
            if(edge.node == node2) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String print = "";
        for(List<Edge> e : node) {
            print += e.toString();
            print += "\n";
        }
        return print;
    }

    public void bfs() {
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        boolean[] visited = new boolean[node.length];
        int random = 0;//new Random().nextInt(node.length-1);
        System.out.println("rand : " + random);
        queue.add(random);
        visited[random] = true;
        while (queue.size() != 0) {
            int currentNode = queue.poll();
            output.add(currentNode);
            List<Edge> edges = node[currentNode];
            for (Edge edge : edges) {
                if (!visited[edge.node]) {
                    visited[edge.node] = true;
                    queue.add(edge.node);
                }
            }
        }
        System.out.println("bfs: " + output);
    }

    public boolean dfs(int s, int d) {
        Stack<Integer> stack = new Stack<>();
        LinkedList<Integer> output = new LinkedList<>();
        boolean[] visited = new boolean[verrtexNo];
        int random = s;//new Random().nextInt(node.length-1);
        System.out.println("rand : " + random);
        stack.push(random);
        visited[random] = true;
        while (stack.size() != 0) {
            int currentNode = stack.pop();
            output.add(currentNode);
            List<Edge> edges = node[currentNode];
            for (Edge edge : edges) {
                if (edge.node == d)
                    return true;
                if (!visited[edge.node]) {
                    visited[edge.node] = true;
                    stack.push(edge.node);
                }
            }
        }
        return false;
    }

    public void dfs() {
        Stack<Integer> stack = new Stack<>();
        LinkedList<Integer> output = new LinkedList<>();
        boolean[] visited = new boolean[verrtexNo];
        int random = 0;//new Random().nextInt(node.length-1);
        System.out.println("rand : " + random);
        stack.push(random);
        visited[random] = true;
        while (stack.size() != 0) {
            int currentNode = stack.pop();
            output.add(currentNode);
            List<Edge> edges = node[currentNode];
            for (Edge edge : edges) {
                if (!visited[edge.node]) {
                    visited[edge.node] = true;
                    stack.push(edge.node);
                }
            }
        }
        System.out.println("dfs: " + output);
    }

    private void dfsRecursive(int v) {
        boolean[] visited = new boolean[verrtexNo];
        List<Integer> output = new LinkedList<>();
        dfsRecursion(v, visited, output);
        System.out.println("dfsRecursive: " + output);
    }

    private void dfsRecursion(int v, boolean[] visited, List<Integer> output) {
        List<Edge> edges = node[v];
        visited[v] = true;
        output.add(v);
        if(edges == null) return;
        for(Edge edge: edges) {
            if(!visited[edge.node]) {
                dfsRecursion(edge.node, visited, output);
            }
        }
    }

    private int[] topologicalSort() {
        Stack<Integer> s = new Stack();
        //1 : white
        //2 : gray
        //3 : black
        int[] color = new int[verrtexNo];
        boolean hasCycle = false;

        //add all vertex to white set
        for(int j = 0; j < verrtexNo; j++) {
           color[j] = 1;
        }

        for(int i = 0 ; i < verrtexNo; i++) {
            if(color[i] == 1) {//white
                if (!hasCycle && topologicalSortRecursion(i, s, color)) {
                    hasCycle = true;
                }
            }
        }

        if(hasCycle) {
            return new int[0];
        } else {
            int[] topSort = new int[s.size()];
            int index = 0;
            while(!s.isEmpty()) {
                topSort[index++] = s.pop();
            }
            return topSort;
        }
    }

    private boolean topologicalSortRecursion(int vertex, Stack<Integer> s, int[] color) {
        //move from white to gray
        color[vertex] = 2; //gray
        for(Edge edge: this.node[vertex]) {
            //if in black, means it is done exploring
            if(color[edge.node] == 3) { //black
                continue;
            }

            //if in gray, we visited a node which is part of current recursion i.e. cycle
            if(color[edge.node] == 2) {//gray
                return true;
            }

            if(topologicalSortRecursion(edge.node, s, color)) {
                return true;
            }
        }
        //move from grey set to black as we are done exploring current node
        color[vertex] = 3; //black
        s.push(vertex);
        return false;
    }

    public void dijkstras(int startingNode) {
        HashSet<Integer> sptSet = new HashSet<>();
        HashMap<Integer, Integer> minDist = new HashMap<>();
        for(int j = 0 ; j < verrtexNo; j++) {
            minDist.put(j, Integer.MAX_VALUE);
        }
        minDist.put(startingNode, 0);
        for (int i = 0; i < verrtexNo; i++) {
            int mIndex = minNode(sptSet, minDist);
            sptSet.add(mIndex);
            List<Edge> edgeList = node[mIndex];
            if(edgeList != null) {
                for (Edge node : edgeList) {
                    if (!sptSet.contains(node.node)
                            && minDist.get(node.node) > minDist.get(mIndex) + node.weight) {
                        minDist.put(node.node, minDist.get(mIndex) + node.weight);
                    }
                }
            }
        }
        System.out.println("dijkstra: " + minDist);
    }

    private int minNode(HashSet<Integer> sptSet, HashMap<Integer, Integer> minDist) {
        int mDist = Integer.MAX_VALUE;
        int mIndex = -1;
        Iterator<Integer> keyItr = minDist.keySet().iterator();
        for (Integer index : minDist.keySet()) {
            if (!sptSet.contains(index) && mDist >= minDist.get(index)) {
                mDist = minDist.get(index);
                mIndex = index;
            }
        }
        return mIndex;
    }

    public static void
    main(String[] args) {
        int[][] edges = {{1,0},{2,0},{3,1},{3,2}};
        int vertNo = 4;
        Graph g = new Graph(vertNo);
        for(int[] edge : edges) {
            g.addEdge(edge[1],edge[0],1);
        }
        g.dfsRecursive(0);
        System.out.println(Arrays.toString(g.topologicalSort()));
    }
}

class Edge {
    int node;
    int weight;

    Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + node + "," + weight + ")";
    }
}
