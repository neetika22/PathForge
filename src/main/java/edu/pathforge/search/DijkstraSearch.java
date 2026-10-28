package edu.pathforge.search;
import edu.pathforge.graph.Graph;
import java.util.*;
public class DijkstraSearch<N> implements Pathfinder<N>  {
    public DijkstraSearch() {}
    @Override
public PathResult<N> findPath(
    Graph<N> graph, N start, N goal) {
        SearchSupport.validate(graph, start, goal);
        Map<N,Double> distance = new HashMap<N,Double>();
        Map<N,N> previous = new HashMap<N,N>();
        PriorityQueue<Entry<N>> queue = new PriorityQueue<Entry<N>>();
        distance.put(start, 0.0); queue.add(new Entry<N>(start, 0.0));
        int expanded = 0;
        while (!queue.isEmpty()) {
            Entry<N> e = queue.poll();
            Double best = distance.get(e.node);
            if (best == null || e.cost > best) continue;
            if (e.node.equals(goal)) break;
            expanded++;
            for (N next : graph.getNeighbors(e.node)) {
                double candidate = best + graph.getCost(e.node, next);
                Double old = distance.get(next);
                if (old == null || candidate < old) {
                    distance.put(next, candidate); previous.put(next, e.node);
                    queue.add(new Entry<N>(next, candidate));
                }
            }
        }
        return SearchSupport.build(start, goal, previous, distance, expanded);
    }
    static final class Entry<N> implements Comparable<Entry<N>> {
        final N node; final double cost;
        Entry(N node, double cost) { this.node = node; this.cost = cost; }
        public int compareTo(Entry<N> other) { return Double.compare(cost, other.cost); }
    }
}
