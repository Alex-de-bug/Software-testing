package com.lab1;

import com.lab1.util.Graph;

import java.util.*;

public class BFSClass {
    public static String[] bfs(Graph graph, String start, String target) {
        Map<String, List<String>> adj = graph.getAdj();

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(Collections.singletonList(start));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastNode = path.getLast();

            if (lastNode.equals(target)) {
                return path.toArray(new String[0]);
            }

            for (String neighbor : adj.getOrDefault(lastNode, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        return new String[0];
    }
}
