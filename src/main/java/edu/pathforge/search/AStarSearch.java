package edu.pathforge.search;
import edu.pathforge.graph.Graph;
import java.util.*;
public class AStarSearch<N> implements Pathfinder<N> {
    private final Heuristic<N> heuristic;

    public AStarSearch(Heuristic<N> heuristic) {
        if (heuristic == null) {
            throw new IllegalArgumentException("Heuristic cannot be null");
        }
        this.heuristic = heuristic;
    }
 @Override
public PathResult<N> findPath(
    Graph<N> graph, N start, N goal) {
        SearchSupport.validate(graph, start, goal);
        if (heuristic == null) throw new IllegalArgumentException("Heuristic is required");
        Map<N,Double> g = new HashMap<N,Double>();
        Map<N,N> previous = new HashMap<N,N>();
        PriorityQueue<Entry<N>> open = new PriorityQueue<Entry<N>>();
        g.put(start, 0.0);
        open.add(new Entry<N>(start, checkedEstimate(heuristic, start, goal), 0.0));
        int expanded = 0;
        while (!open.isEmpty()) {
            Entry<N> e = open.poll();
            Double known = g.get(e.node);
            if (known == null || e.gAtInsert > known) continue;
            if (e.node.equals(goal)) return SearchSupport.build(start, goal, previous, g, expanded);
            expanded++;
            for (N next : graph.getNeighbors(e.node)) {
                double tentative = known + graph.getCost(e.node, next);
                Double old = g.get(next);
                if (old == null || tentative < old) {
                    g.put(next, tentative); previous.put(next, e.node);
                    open.add(new Entry<N>(next, tentative + checkedEstimate(heuristic, next, goal), tentative));
                }
            }
        }
        return null;
    }
    private static <N> double checkedEstimate(Heuristic<N> h, N node, N goal) {
        double value = h.estimate(node, goal);
        if (Double.isNaN(value) || Double.isInfinite(value) || value < 0)
            throw new IllegalArgumentException("Heuristic must be finite and non-negative");
        return value;
    }
    private static final class Entry<N> implements Comparable<Entry<N>> {
        final N node; final double priority; final double gAtInsert;
        Entry(N node, double priority, double gAtInsert) { this.node=node; this.priority=priority; this.gAtInsert=gAtInsert; }
        public int compareTo(Entry<N> other) { return Double.compare(priority, other.priority); }
    }
}
