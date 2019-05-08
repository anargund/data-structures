package com.graph;

import java.util.LinkedList;
import java.util.Queue;

public class IsBipartite {
    public boolean isBipartite(int[][] graph) {
        //0 : not visited 1/2 : visited
        int[] color = new int[graph.length];
        //BFS traversal
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0 ; i < graph.length ; i++) {
            //if not visited already
            if(color[i] == 0) {
                //add to Queue and color it as 1
                queue.add(i);
                color[i] = 1;
                while(!queue.isEmpty()) {
                    int current = queue.poll();
                    int[] edges = graph[current];
                    for(int edge : edges) {
                        //if not visited
                        if(color[edge] == 0) {
                            //color it opposite of color of current node
                            //if current is colored 1 : 3-1 = 2
                            //if current is colored 2 : 3-2 = 1
                            color[edge] = 3 - color[current];
                            queue.add(edge);
                        } else if(color[edge] == color[current]) {
                            //if it is already visited, check color
                            //if colors are same, it is not bipartite
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
