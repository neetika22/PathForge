package edu.pathforge.search;

import edu.pathforge.graph.Graph;

public interface Pathfinder<N> {
    PathResult<N> findPath(Graph<N> graph, N start, N goal);
}