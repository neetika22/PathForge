package edu.pathforge.search;
import java.util.*;
public final class PathResult<N> {
    private final List<N> path;
    private final double totalCost;
    private final int expandedNodes;
    public PathResult(List<N> path, double totalCost, int expandedNodes) {
        this.path = Collections.unmodifiableList(new ArrayList<N>(path));
        this.totalCost = totalCost; this.expandedNodes = expandedNodes;
    }
    public List<N> getPath() { return path; }
    public double getTotalCost() { return totalCost; }
    public int getExpandedNodes() { return expandedNodes; }
    public String toString() {
        return "Path: " + path + "\nTotal cost: " + totalCost + "\nExpanded nodes: " + expandedNodes;
    }
}
