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
