package edu.pathforge.search;
import edu.pathforge.graph.Graph;
import java.util.*;
public class BreadthFirstSearch<N> implements Pathfinder<N> {
    public BreadthFirstSearch() {}
   @Override
public PathResult<N> findPath(Graph<N> graph, N start, N goal) {
        SearchSupport.validate(graph, start, goal);
        Queue<N> queue = new ArrayDeque<N>();
        Set<N> seen = new HashSet<N>();
        Map<N,N> previous = new HashMap<N,N>();
        queue.add(start); seen.add(start);
        while (!queue.isEmpty()) {
            N current = queue.remove();
            if (current.equals(goal)) break;
            for (N next : graph.getNeighbors(current)) if (seen.add(next)) {
                previous.put(next, current); queue.add(next);
            }
        }
       if (!seen.contains(goal)) {
    return new PathResult<N>(
        Collections.<N>emptyList(), Double.POSITIVE_INFINITY, 0);
}
        List<N> path = new ArrayList<N>();
        for (N at = goal; at != null; at = previous.get(at)) {
            path.add(at); if (at.equals(start)) break;
        }
       Collections.reverse(path);

return new PathResult<N>(path, path.size() - 1, seen.size());
    }
}

