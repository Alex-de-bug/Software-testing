package com.lab1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab1.util.Graph;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BFSClassTest {

    private static final String FILE_PATH = "tests/graph.tests.json";

    private Graph oneWayGraph() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        graph.addEdge("E", "F");
        return graph;
    }

    public Graph cycleGraph() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        return graph;
    }

    public Graph disconnectedGraph() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("C", "D");
        return graph;
    }

    public Graph treeGraph() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        return graph;
    }

    @Test    
    public void testBFS_oneWay_fromStartToEnd_thenPath() {
        Graph graph = oneWayGraph();

        String[] expected = {"A", "B", "C", "D", "E", "F"};
        String[] actual = BFSClass.bfs(graph, "A", "F");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_oneWay_fromEndToStart_thenNoPath() {
        Graph graph = oneWayGraph();

        String[] expected = new String[0];
        String[] actual = BFSClass.bfs(graph, "F", "A");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_oneWay_invalidEnd_thenNoPath() {
        Graph graph = oneWayGraph();

        String[] expected = new String[0];
        String[] actual = BFSClass.bfs(graph, "A", "G");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_oneWay_invalidStart_thenNoPath() {
        Graph graph = oneWayGraph();

        String[] expected = new String[0];
        String[] actual = BFSClass.bfs(graph, "G", "A");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_oneWay_fromThirdToEnd_thenCutPath() {
        Graph graph = oneWayGraph();

        String[] expected = {"C", "D", "E", "F"};
        String[] actual = BFSClass.bfs(graph, "C", "F");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_oneWay_fromStartToFourth_thenCutPath() {
        Graph graph = oneWayGraph();

        String[] expected = {"A", "B", "C", "D"};
        String[] actual = BFSClass.bfs(graph, "A", "D");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_oneWay_fromSamePoint_thenPoint() {
        Graph graph = oneWayGraph();

        String[] expected = {"B"};
        String[] actual = BFSClass.bfs(graph, "B", "B");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_cycle_fromStartToEnd_thenPath() {
        Graph graph = cycleGraph();

        String[] expected = {"A", "B", "C"};
        String[] actual = BFSClass.bfs(graph, "A", "C");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_cycle_invalidEnd_thenNoPath() {
        Graph graph = cycleGraph();

        String[] expected = {};
        String[] actual = BFSClass.bfs(graph, "A", "D");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_disconnected_invalidPath_thenNoPath() {
        Graph graph = disconnectedGraph();

        String[] expected = new String[0];
        String[] actual = BFSClass.bfs(graph, "A", "D");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_tree_fromStartToEnd_thenPath() {
        Graph graph = treeGraph();

        String[] expected = {"A", "C", "F"};
        String[] actual = BFSClass.bfs(graph, "A", "F");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_tree_fromDifferentEdge_thenNoPath() {
        Graph graph = treeGraph();

        String[] expected = {};
        String[] actual = BFSClass.bfs(graph, "B", "F");

        assertEquals(List.of(expected), List.of(actual));
    }

    @Test
    public void testBFS_fromFile() {
        File file = new File(FILE_PATH);
        String absolutePath = file.getAbsolutePath();

        if (!file.exists()) {
            System.out.println("File not found: " + absolutePath + ". Tests count as passed.");
            return;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode testCases = objectMapper.readTree(file);

            for (JsonNode testCase : testCases) {
                Graph graph = new Graph();

                for (JsonNode connection : testCase.get("connections")) {
                    String from = connection.get("from").asText();
                    String to = connection.get("to").asText();
                    graph.addEdge(from, to);
                }

                String start = testCase.get("start").asText();
                String target = testCase.get("target").asText();

                List<String> expectedPath = new ArrayList<>();
                for (JsonNode node : testCase.get("expected")) {
                    expectedPath.add(node.asText());
                }

                String[] actual = BFSClass.bfs(graph, start, target);

                assertEquals(expectedPath, List.of(actual));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while loading JSON tests for BFS: " + e.getMessage(), e);
        }
    }
}