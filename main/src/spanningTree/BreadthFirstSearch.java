package src.spanningTree;

import src.graphTools.Arc;
import src.graphTools.Edge;
import src.graphTools.Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class BreadthFirstSearch {

    // BFS traversal from a given source source
    public static ArrayList<Arc> generateSpanningTree(Graph graph, int source) {
        // List of arcs in the order of the node's discovery
        ArrayList<Arc> spanningTree = new ArrayList<>();

        // Mark all the vertices as not discovered
        boolean[] discovered = new boolean[graph.order];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as discovered and enqueue it
        discovered[source] = true;
        queue.add(source);

        // While there are still some nodes in the queue
        while (queue.size() != 0) {
            // Dequeue a vertex from queue
            source = queue.poll();

            // Look at every arc going out of that vertex
            for (Arc outgoingArcOfSource : graph.outAdjacency.get(source)) {
                // Get the destination of the current arc
                int destination = outgoingArcOfSource.edge.dest;
                if (destination == source) destination = outgoingArcOfSource.edge.source;
                // If that destination has not been discovered yet
                if (!discovered[destination]) {
                    // Add the arc to the spanning tree
                    spanningTree.add(outgoingArcOfSource);
                    // Set the destination as discovered
                    discovered[destination] = true;
                    // Add the destination to the queue
                    queue.add(destination);
                }
            }
        }

        return spanningTree;
    }
}