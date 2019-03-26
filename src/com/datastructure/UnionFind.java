package com.datastructure;

import java.util.ArrayList;

class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int find(int x) {
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

    public boolean union(int elementA, int elementB) {
        int fA = find(elementA);
        int fB = find(elementB);
        if(fA == fB) return false;
        if(rank[fA] <= rank[fB]) {
            parent[fA] = fB;
            rank[fB] += rank[fA];
        } else {
            parent[fB] = fA;
            rank[fA] += rank[fB];
        }
        return true;
    }
}