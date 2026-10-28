package edu.pathforge.search;
import edu.pathforge.graph.Graph;
import java.util.*;
final class SearchSupport {
    private SearchSupport() {}
    static <N> void validate(Graph<N> graph, N start, N goal) {
        if (graph == null || start == null || goal == null) throw new IllegalArgumentException("Graph, start, and goal are required");
        if (!graph.containsNode(start) || !graph.containsNode(goal)) throw new IllegalArgumentException("Start and goal must exist in graph");
    }
    static <N> PathResult<N> build(N start, N goal, Map<N,N> previous, Map<N,Double> costs, int expanded) {
        if (!costs.containsKey(goal)) return null;
        List<N> path = new ArrayList<N>();
        N at = goal; path.add(at);
        while (!at.equals(start)) {
            at = previous.get(at);
            if (at == null) return null;
            path.add(at);
        }
        Collections.reverse(path);
        return new PathResult<N>(path, costs.get(goal), expanded);
    }
}
