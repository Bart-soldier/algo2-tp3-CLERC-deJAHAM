package src.spanningTree;

import src.graphTools.Arc;
import src.graphTools.Edge;
import src.graphTools.Graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 3.6 Algorithme de Wilson
 */

public class WilsonAlgorithm {

    public static ArrayList<Arc> SpanningTree(Graph graph) {
        // List of edges
        ArrayList<Arc> spanningTree = new ArrayList<>();

        // List of visited vertices
        ArrayList<Integer> visited = new ArrayList<>();

        // The indices of the table correspond to the vertices and the Integer at that index is the successor in the successor, if it exists (-1 otherwise)
        int[] successor = new int[graph.order];
        Arrays.fill(successor, -1);

        // Add the initial vertex to the visited list
        visited.add(graph.order - 1);

        boolean test = true;
        int vertex;
        int startingVertex = -1;
        int randomNeighbor = -1;

        // Until all the vertices have been visited
        while(visited.size() < graph.order) {
            // If you're starting a new successor
            if(test) {
                // Get a random vertex that is not already in the visited list
                do {
                    startingVertex = (int) (Math.random() * graph.order);
                }
                while(visited.contains(startingVertex));
                vertex = startingVertex;
                test = false;
            }
            else vertex = randomNeighbor;

            // List of the vertex's neighbors
            ArrayList<Integer> neighbors = new ArrayList<>();

            // For each edge connected to that vertex
            for (Arc arc : graph.outAdjacency.get(vertex)) {
                // Add the connected vertex to the neighbors list
                if (arc.edge.dest != vertex) neighbors.add(arc.edge.dest);
                else neighbors.add(arc.edge.source);
            }

            // Get a random neighbor from the list of neighbors
            randomNeighbor = neighbors.get((int) (Math.random() * neighbors.size()));
            // Update successor : you got to randomNeighbor from vertex
            successor[vertex] = randomNeighbor;

            // If the visited list contains the random neighbor
            if (visited.contains(randomNeighbor)) {
                vertex = startingVertex;
                // Walk the successor until you get to -1
                while(successor[vertex] != -1) {
                    // Add the vertex to the list of visited vertices if it
                    visited.add(vertex);
                    // Add an edge connecting the vertex and its predecessor to the spanning tree
                    spanningTree.add(new Arc(new Edge(vertex, successor[vertex], 0), true));
                    vertex = successor[vertex];
                }
                // Reset the successor
                Arrays.fill(successor, -1);
                test = true;
            }
        }

        return spanningTree;
    }
}
