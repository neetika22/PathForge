package edu.pathforge.graph;
import java.util.*;
public class SimpleGraph<N> implements Graph<N> {
    private final Map<N, Map<N, Double>> adjacency = new LinkedHashMap<N, Map<N, Double>>();
    public void addNode(N node) {
        if (node == null) throw new IllegalArgumentException("Node cannot be null");
        if (!adjacency.containsKey(node)) adjacency.put(node, new LinkedHashMap<N, Double>());
    }
    public void addDirectedEdge(N from, N to, double cost) {
        if (Double.isNaN(cost) || Double.isInfinite(cost) || cost < 0)
            throw new IllegalArgumentException("Edge cost must be finite and non-negative");
        addNode(from); addNode(to); adjacency.get(from).put(to, cost);
    }
    
public void addUndirectedEdge(N a, N b) {
    addUndirectedEdge(a, b, 1.0);
}

    public void addUndirectedEdge(N a, N b, double cost) {
        addDirectedEdge(a, b, cost); addDirectedEdge(b, a, cost);
    }
    public Set<N> getNeighbors(N node) {
        Map<N, Double> edges = adjacency.get(node);
        return edges == null ? Collections.<N>emptySet()
                : Collections.unmodifiableSet(new LinkedHashSet<N>(edges.keySet()));
    }
    public double getCost(N from, N to) {
        Map<N, Double> edges = adjacency.get(from);
        if (edges == null || !edges.containsKey(to))
            throw new IllegalArgumentException("No edge from " + from + " to " + to);
        return edges.get(to);
    }
    public boolean containsNode(N node) { return adjacency.containsKey(node); }
}
