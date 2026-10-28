package edu.pathforge.graph;
import java.util.Set;
public interface Graph<N> {
    Set<N> getNeighbors(N node);
    double getCost(N from, N to);
    boolean containsNode(N node);
}
