package src.spanningTree;

import src.graphTools.Arc;
import src.graphTools.Edge;
import src.graphTools.Graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 3.2 Parcours aléatoire
 */

public class RandomBreadthFirstSearch {

    // BFS traversal from a given source source
    public static ArrayList<Edge> SpanningTree(Graph graph) {

        // Random source
        int source = (int) (Math.random() * graph.order);

        // List of edges in the order of the node's discovery
        ArrayList<Edge> spanningTree = new ArrayList<>();

        // Mark all the vertices as not discovered
        boolean[] discovered = new boolean[graph.order];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as discovered and enqueue it
        discovered[source] = true;
        queue.add(source);

        // While there are still some nodes in the queue
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue in a random fashion
            int randomQueueIndex = (int) (Math.random() * queue.size());
            source = queue.get(randomQueueIndex);
            queue.remove(randomQueueIndex);


            // Look at every arc going out of that vertex
            for(Edge outgoingArcOfSource : graph.adjacency.get(source)) {
                // Get the destination of the current arc
                int destination = outgoingArcOfSource.dest;
                if(destination == source) destination=outgoingArcOfSource.source;
                // If that destination has not been discovered yet
                if(!discovered[destination]) {
                    // Add the arc to the spanning tree
                    spanningTree.add(new Edge(source, destination, 0));
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