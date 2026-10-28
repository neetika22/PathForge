
package edu.pathforge;

import edu.pathforge.graph.SimpleGraph;
import edu.pathforge.search.AStarSearch;
import edu.pathforge.search.DijkstraSearch;
import edu.pathforge.search.Heuristic;
import edu.pathforge.search.PathResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathForgeTest {

    @Test
    public void dijkstraFindsLowestCostPath() {
        SimpleGraph<String> graph = new SimpleGraph<>();
        graph.addUndirectedEdge("A", "B", 2);
        graph.addUndirectedEdge("B", "C", 1);
        graph.addUndirectedEdge("A", "C", 5);

        PathResult<String> result =
                new DijkstraSearch<String>().findPath(graph, "A", "C");

        assertNotNull(result);
        assertEquals(3.0, result.getTotalCost(), 0.001);
    }

    @Test
    public void aStarWithZeroHeuristicMatchesDijkstra() {
        SimpleGraph<String> graph = new SimpleGraph<>();
        graph.addUndirectedEdge("A", "B", 2);
        graph.addUndirectedEdge("B", "C", 1);
        graph.addUndirectedEdge("A", "C", 5);

        Heuristic<String> heuristic = new Heuristic<String>() {
            @Override
            public double estimate(String node, String goal) {
                return 0.0;
            }
        };

AStarSearch<String> astar = new AStarSearch<String>(heuristic);
PathResult<String> result = astar.findPath(graph, "A", "C");
        assertNotNull(result);
        assertEquals(3.0, result.getTotalCost(), 0.001);
    }
}
