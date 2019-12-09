package src.spanningTree;

import src.graphTools.Edge;
import src.graphTools.Graph;

import java.util.ArrayList;

/**
 * 3.4 Algorithme d'Aldous-Broder
 */

public class AldousBroderAlgorithm {

    public static ArrayList<Edge> SpanningTree(Graph graph) {
        // List of edges
        ArrayList<Edge> spanningTree = new ArrayList<>();

        // List of visited vertices
        ArrayList<Integer> visited = new ArrayList<>();

        // Get a random vertex
        int vertex = (int) (Math.random() * graph.order);
        // Add the vertex to the visited list
        visited.add(vertex);

        // Until all the vertices have been visited
        while(visited.size() < graph.order) {
            // List of a vertex's neighbors
            ArrayList<Integer> neighbors = new ArrayList<>();

            // For each edge connected to that vertex
            for (Edge edge : graph.adjacency.get(vertex)) {
                // Add the connected vertex to the neighbors list
                if (edge.dest != vertex) neighbors.add(edge.dest);
                else neighbors.add(edge.source);
            }

            // Get a random neighbor from the list of neighbors
            int randomNeighbor = neighbors.get((int) (Math.random() * neighbors.size()));

            // If the visited list does not contain the random neighbor
            if (!visited.contains(randomNeighbor)) {
                // Add the random neighbor to the list of visited vertices
                visited.add(randomNeighbor);
                // Add an edge connecting the vertex and the random neighbor to the spanning tree
                spanningTree.add(new Edge(vertex, randomNeighbor, 0));
                // The randomNeighbor is now the new vertex
                vertex = randomNeighbor;
            }
            else vertex = randomNeighbor;
        }

        return spanningTree;
    }
}
