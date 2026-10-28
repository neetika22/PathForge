package edu.pathforge.demo;
import edu.pathforge.graph.SimpleGraph;
import edu.pathforge.search.*;
public class PathForgeDemo {
    public static void main(String[] args) {
        SimpleGraph<String> graph = new SimpleGraph<String>();
        graph.addUndirectedEdge("A", "B", 2);
        graph.addUndirectedEdge("A", "C", 4);
        graph.addUndirectedEdge("B", "C", 1);
        graph.addUndirectedEdge("B", "D", 5);
        graph.addUndirectedEdge("C", "D", 1);
        graph.addUndirectedEdge("C", "E", 3);
        graph.addUndirectedEdge("D", "F", 2);
        graph.addUndirectedEdge("E", "F", 2);
        Heuristic<String> zeroHeuristic = new Heuristic<String>() {
            public double estimate(String node, String goal) { return 0.0; }
        };
   PathResult<String> aStarResult =
    new AStarSearch<String>(zeroHeuristic).findPath(graph, "A", "F");

System.out.println(aStarResult);
        System.out.println("=== PathForge Graph Search Demo ===");
        System.out.println("\nA* result:");
        
       Pathfinder<String> dijkstra =
        new DijkstraSearch<String>();

PathResult<String> dijkstraResult =
        dijkstra.findPath(graph, "A", "F");

System.out.println(dijkstraResult);
       dijkstra.findPath(graph, "A", "F");
        System.out.println("\nBFS path (fewest edges; weights ignored):");
     Pathfinder<String> bfs =
    new BreadthFirstSearch<String>();

PathResult<String> bfsResult =
    bfs.findPath(graph, "A", "F");

System.out.println(bfsResult);
    }
}
