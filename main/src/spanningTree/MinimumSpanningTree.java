package src.spanningTree;

import java.util.ArrayList;
import java.util.LinkedList;

import src.graphTools.Arc;
import src.graphTools.Edge;
import src.graphTools.Graph;

/**
 * 3.1 Arbres couvrant de poids minimum aléatoire
 */

public class MinimumSpanningTree {

    public ArrayList<Edge> SpanningTreeMinimumWeight(Graph graph) {
        // Create empty spanning tree
        ArrayList<Edge> SpanningTree = new ArrayList<>();

        // For each vertex in the graph
        for(int vertex = 0 ; vertex < graph.order; vertex++) {
            // For each edge of that vertex
            for(Edge edge : graph.adjacency.get(vertex)) {
                // Set weight to a random number between [0, 1]
                edge.weight = Math.random() * 1;
            }
        }

        return primAlgorithm(graph);
    }

    // Prim's algorithm from a random source
    private static ArrayList<Edge> primAlgorithm(Graph graph)
    {
        // Random source
        int source = (int) Math.random() * (graph.order - 1);

        // List of arcs in the order of the node's discovery
        ArrayList<Edge> minimumSpanningTree = new ArrayList<>();

        // Mark all the vertices as not discovered
        boolean[] discovered = new boolean[graph.order];

        // Create a queue for the algorithm
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as discovered and enqueue it
        discovered[source] = true;
        queue.add(source);

        // While there are still some nodes in the queue
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue
            source = queue.poll();

            // Reset weight variables
            double outgoingArcOfSourceWeight;
            double minWeight = -1;
            int destination = -1;

            // Look at every arc going out of that vertex
            for(Arc outgoingArcOfSource : graph.outAdjacency.get(source)) {
                // If this arc's destination has not been discovered yet
                if(!discovered[outgoingArcOfSource.edge.dest]) {
                    // Get its weight
                    outgoingArcOfSourceWeight = Math.min(minWeight, outgoingArcOfSource.edge.weight);

                    // Check if outgoingArcOfSource's weight is strictly smaller than the current minWeight
                    if (outgoingArcOfSourceWeight < minWeight) {
                        // If so, set the outgoingArcOfSource's weight as the new minWeight
                        minWeight = outgoingArcOfSourceWeight;
                        // And set outgoingArcOfSource's destination as the new destination
                        destination = outgoingArcOfSource.edge.dest;
                    }
                }
            }

            // If we have found a destination
            if(destination != -1) {
                // Add the edge to the minimum spanning tree
                minimumSpanningTree.add(new Edge(source, destination, 0));
                // Set the destination as discovered
                discovered[destination] = true;
                // Add the destination to the queue
                queue.add(destination);
            }
        }

        return minimumSpanningTree;
    }
}
