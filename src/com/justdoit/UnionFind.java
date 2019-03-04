package com.justdoit;

import java.util.ArrayList;

class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        ArrayList<Integer> path = new ArrayList<>();
        while(parent[x] != x) {
            path.add(x);
            x = parent[x];
        }
        //path compression
        for(int i : path) {
            parent[i] = x;
        }
        return x;
    }

    public boolean union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if(fx == fy) return false;
        if(rank[fx] <= rank[fy]) {
            parent[fx] = fy;
            rank[fy] += rank[fx];
        } else {
            parent[fy] = fx;
            rank[fx] += rank[fy];
        }
        return true;
    }
}