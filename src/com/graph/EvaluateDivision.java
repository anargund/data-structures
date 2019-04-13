package com.graph;

import java.util.*;

public class EvaluateDivision {

    HashMap<String, List<Edge>> nodeMap = new HashMap<>();

    public static void main(String[] args) {
        EvaluateDivision division = new EvaluateDivision();
        String[][] eq = { {"a","b"},{"b","c"} };
        double[] v = {2.0,3.0};
        String[][] q = { {"a","c"},{"b","c"},{"a","e"},{"a","a"},{"x","x"} };
        System.out.println(Arrays.toString(division.calcEquation(eq,v,q)));
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        //build map
        for(int i = 0; i < equations.length ; i++) {
            List<Edge> nodes = nodeMap.get(equations[i][0]);
            if(nodes == null) {
                nodes = new ArrayList<>();
                nodeMap.put(equations[i][0],nodes);
            }
            nodes.add(new Edge(equations[i][1], values[i]));
            List<Edge> nodes1 = nodeMap.get(equations[i][1]);
            if(nodes1 == null) {
                nodes1 = new ArrayList<>();
                nodeMap.put(equations[i][1],nodes1);
            }
            nodes1.add(new Edge(equations[i][0], 1/values[i]));
        }
        double[] ans = new double[queries.length];
        for(int i = 0; i < queries.length; i++) {
            ans[i] = dfs(queries[i][0], queries[i][1]);
        }
        return ans;
    }

    private double dfs(String a, String b) {
        Stack<String> stack = new Stack<>();
        Stack<Double> vStack = new Stack<>();
        Set<String> visited = new HashSet<>();
        stack.push(a);
        vStack.push(1.0);
        visited.add(a);
        while(!stack.isEmpty()) {
            String node = stack.pop();
            double cVal = vStack.pop();
            List<Edge> edges = nodeMap.get(node);
            if(edges != null) {
                for(Edge edge : edges) {
                    if(edge.node.equals(b)) {
                        return edge.value * cVal;
                    }
                    if(!visited.contains(edge.node)) {
                        stack.push(edge.node);
                        visited.add(edge.node);
                        vStack.push(cVal * edge.value);
                    }
                }
            }
        }
        return -1;
    }

    class Edge {
        String node;
        double value;
        public Edge(String node, double value) {
            this.node = node;
            this.value = value;
        }
    }
}
