package com.lab1.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<String, List<String>> adj;

    public Graph() {
        this.adj = new HashMap<>();
    }

    public void addEdge(String u, String v) {
        adj.putIfAbsent(u, new ArrayList<>());
        adj.putIfAbsent(v, new ArrayList<>());
        adj.get(u).add(v);
    }

    public Map<String, List<String>> getAdj() {
        return adj;
    }
}
