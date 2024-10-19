package graphDataStructures;

import java.util.*;

public class GraphWithAdjacencyList {
    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    Map<String, Node> nodes = new HashMap();
    Map<Node, List<Node>> adjacencyList = new HashMap();

    public void addNode(String label) {
        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void removeNode(String label) {
        Node node = nodes.get(label);
        if (node == null) {
            return;
        }
        Node removedNode = nodes.remove(label);

        for (List<Node> nodeList : adjacencyList.values()) {
            nodeList.remove(removedNode);
        }
    }

    public void addEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        if (fromNode == null) {
            throw new IllegalArgumentException();
        }

        Node toNode = nodes.get(to);
        if (toNode == null) {
            throw new IllegalArgumentException();
        }

        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException();
        }


        adjacencyList.get(fromNode).remove(toNode);
    }

    public void depthFirstTraversal(String label) {
        Node root = nodes.get(label);

        if (root == null) {
            throw new IllegalArgumentException();
        }

        depthFirstTraversal(root, new HashSet<>());
    }

    private void depthFirstTraversal(Node node, Set<Node> visitedNodes) {
        System.out.println(node.label);
        visitedNodes.add(node);

        for (Node neighbor : adjacencyList.get(node)) {
            if (!visitedNodes.contains(neighbor)) {
                depthFirstTraversal(neighbor, visitedNodes);
            }
        }
    }

    public void breadthFirstTraversal(String label) {
        Node root = nodes.get(label);
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Queue<Node> queue = new ArrayDeque<>();

        breadthFirstTraversal(root, queue, new HashSet<>());
    }

    private void breadthFirstTraversal(Node node, Queue<Node> queue, Set<Node> visitedNodes) {
        System.out.println(node.label);
        visitedNodes.add(node);


        for (Node neighbor : adjacencyList.get(node)) {
            if (!visitedNodes.contains(neighbor)) {
                queue.add(neighbor);
                breadthFirstTraversal(queue.remove(), queue, visitedNodes);
            }
        }
    }

    public void print() {
        for (var entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
