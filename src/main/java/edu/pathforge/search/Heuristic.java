package edu.pathforge.search;
public interface Heuristic<N> {
    double estimate(N node, N goal);
}
