package com.datastructure;

import java.util.*;

public class Graph {
    private List<Edge>[] node;
    private HashMap<Integer, List<Edge>> nodeMap;
    private int verrtexNo;

    public Graph(int n) {
        verrtexNo = n;
        node = new LinkedList[n];
        nodeMap = new HashMap<>();
        for (int i = 0; i < node.length; i++) {
            node[i] = new LinkedList<>();
        }
    }

    public void addEdge(int node1, int node2, int weight) {
        node[node1].add(0, new Edge(node2, weight));
        List<Edge> nodes;
        if (nodeMap.containsKey(node1)) {
            nodes = nodeMap.get(node1);
        } else {
            nodes = new ArrayList<>();
            nodeMap.put(node1, nodes);
        }
        nodes.add(new Edge(node2, weight));
    }

    public boolean isConnected(int node1, int node2) {
//        for(Edge edge : node[node1]) {
//            if(edge.node == node2) return true;
//        }
        if (nodeMap.containsKey(node1)) {
            List<Edge> nodes = nodeMap.get(node1);
            for (Edge node : nodes) {
                if (node.node == node2) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String print = "";
//        for(int i = 0 ; i < node.length; i++) {
//            print += node[i].toString();
//            print += "\n";
//        }
        return nodeMap.values().toString();
//        return print;
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

    public void dfsRecursive(int v) {
        boolean[] visited = new boolean[verrtexNo];
        List<Integer> output = new LinkedList<>();
        dfsRecursion(v, visited, output);
        System.out.println("dfsRecursive: " + output);
    }

    private void dfsRecursion(int v, boolean[] visited, List<Integer> output) {
        List<Edge> edges = nodeMap.get(v);
        visited[v] = true;
        output.add(v);
        if(edges == null) return;
        for(Edge edge: edges) {
            if(!visited[edge.node]) {
                dfsRecursion(edge.node, visited, output);
            }
        }
    }

    public void topologicalSort() {
        Stack<Integer> s = new Stack();
        boolean[] visited = new boolean[verrtexNo];

        for(Integer key : nodeMap.keySet()) {
            if(!visited[key])
                topologicalSortRecursion(key, s, visited);
        }

        System.out.println("topologicalSort: " + s);
    }

    private void topologicalSortRecursion(int vertex, Stack<Integer> s, boolean[] visited) {
        visited[vertex] = true;
        List<Edge> edges = nodeMap.get(vertex);
        if(edges != null) {
            for (Edge edge : edges) {
                if (!visited[edge.node])
                    topologicalSortRecursion(edge.node, s, visited);
            }
        }
        s.push(vertex);
    }

    public void dijkstras(int startingNode) {
        HashSet<Integer> sptSet = new HashSet<>();
        HashMap<Integer, Integer> minDist = new HashMap<>();
        Iterator<Integer> keyIterator = nodeMap.keySet().iterator();
        for(int j = 0 ; j < verrtexNo; j++) {
            minDist.put(j, Integer.MAX_VALUE);
        }
        minDist.put(startingNode, 0);
        for (int i = 0; i < verrtexNo; i++) {
            int mIndex = minNode(sptSet, minDist);
            sptSet.add(mIndex);
            List<Edge> edgeList = nodeMap.get(mIndex);
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

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0,1,1);
        g.addEdge(0,2,1);
        g.addEdge(0,3,1);
        g.addEdge(2,3,1);
        g.addEdge(1,3,1);
        g.addEdge(1,2,1);
        g.addEdge(3,0,1);
        g.dfsRecursive(0);
        g.topologicalSort();
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
