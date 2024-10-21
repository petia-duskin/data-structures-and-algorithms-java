package graphDataStructures;

import java.util.*;

public class WeightedGraph {
    private class Node {
        private String label;
        private List<Edge> edges = new ArrayList<>();

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }

        public void addEdge(Node to, int weight) {
            edges.add(new Edge(this, to, weight));
        }

        public List<Edge> getEdges() {
            return edges;
        }

    }

    private class Edge {
        private Node from;
        private Node to;
        private int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "-(" + weight + ")>" + to;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();

    public void addNode(String label) {
        nodes.putIfAbsent(label, new Node(label));
    }

    public void addEdge(String from, String to, int weight) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException();
        }

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public void dijkstrasShortestPath(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException();
        }

        Map<Node, Edge> shortestPath = new HashMap<>();
        Set<Node> visitedNodes = new HashSet<>();

        for (Node node : nodes.values()) {
            shortestPath.put(node, new Edge(node, null, Integer.MAX_VALUE));
        }

        shortestPath.get(fromNode).weight = 0;

        dijkstraShortestPath(fromNode, visitedNodes, shortestPath);

        Stack<Node> path = new Stack<>();
        path.push(toNode);
        Node current = shortestPath.get(toNode).to;
        while (current != null) {
            path.push(current);
            current = shortestPath.get(current).to;
        }


        while (!path.isEmpty()) {
            System.out.println(path.pop());
        }
        System.out.println(shortestPath);
    }

    private void dijkstraShortestPath(Node node, Set<Node> visitedNodes, Map<Node, Edge> shortestPath) {
        visitedNodes.add(node);

        for (Edge neighbour : node.getEdges()) {
            if (!visitedNodes.contains(neighbour.to)) {
                int distance = shortestPath.get(node).weight + neighbour.weight;

                if (distance < shortestPath.get(neighbour.to).weight) {
                    shortestPath.put(neighbour.to, new Edge(neighbour.to, node, distance));
                }
            }
        }

        for (Edge neighbour : node.getEdges()) {
            if (!visitedNodes.contains(neighbour.to)) {
                dijkstraShortestPath(neighbour.to, visitedNodes, shortestPath);
            }
        }
    }

    // Second implementation of dijkstraShortestPath algorithm

    private class NodeEntry {
        private Node node;
        private int weight;

        public NodeEntry(Node node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public Path getShortestPath(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException();
        }

        Map<Node, Integer> distances = new HashMap<>();
        for (Node node : nodes.values()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        distances.replace(fromNode, 0);

        Map<Node, Node> previousNodes = new HashMap<>();

        Set<Node> visited = new HashSet<>();

        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparingInt(ne -> ne.weight));

        queue.add(new NodeEntry(fromNode, 0));

        while (!queue.isEmpty()) {
            Node current = queue.remove().node;
            visited.add(current);

            for (Edge edge : current.getEdges()) {
                if (visited.contains(edge.to)) {
                    continue;
                }

                int newDistance = distances.get(current) + edge.weight;
                if (newDistance < distances.get(edge.to)) {
                    distances.replace(edge.to, newDistance);
                    previousNodes.put(edge.to, current);
                    queue.add(new NodeEntry(edge.to, newDistance));
                }
            }
        }

        return buildPath(previousNodes, toNode);
    }

    private Path buildPath(Map<Node, Node> previousNodes, Node toNode) {
        Stack<Node> stack = new Stack<>();
        stack.push(toNode);
        Node previous = previousNodes.get(toNode);
        while (previous != null) {
            stack.push(previous);
            previous = previousNodes.get(previous);
        }

        Path path = new Path();
        while (!stack.isEmpty()) {
            path.add(stack.pop().label);
        }

        return path;
    }

    public void print() {
        for (Node node : nodes.values()) {
            System.out.println(node + " -> " + node.getEdges());
        }
    }
}
