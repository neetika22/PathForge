# PathForge
PathForge is an educational, generic Java graph-search library supporting A*, Dijkstra, and breadth-first search (BFS).

## Requirements
- JDK 8 or newer
- Maven, or an IDE that can compile Java

## Run
Open this folder in VS Code. Run `edu.pathforge.demo.PathForgeDemo` from your Java extension, or with Maven:
```bash
mvn test
mvn package
java -cp target/classes edu.pathforge.demo.PathForgeDemo
```

## Algorithms
- **A\*** prioritises nodes using `f(n) = g(n) + h(n)`. For optimal paths, provide an admissible heuristic.
- **Dijkstra** finds a minimum-cost path for non-negative edge weights.
- **BFS** finds a path with the fewest edges; it ignores edge weights.

## Structure
- `Graph<N>`: graph interface independent of node type.
- `SimpleGraph<N>`: weighted adjacency-map implementation.
- `Heuristic<N>`: caller-supplied cost estimate.
- `PathResult<N>`: route, total cost, and expanded-node count.
- `PathForgeDemo`: runnable demonstration.

If you use external code or materials while developing or submitting this project, follow your institution's disclosure rules and identify any third-party material actually reused.
