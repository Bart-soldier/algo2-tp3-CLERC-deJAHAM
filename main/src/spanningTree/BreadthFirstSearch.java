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
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue
            source = queue.poll();

            // Look at every edge connected to that vertex
            for(Edge edge : graph.adjacency.get(source)) {
                // Get the destination of the current edge
                int destination;
                if(edge.dest != source) destination = edge.dest;
                else destination = edge.source;
                // If that destination has not been discovered yet
                if(!discovered[destination]) {
                    // Add the arc to the spanning tree
                    spanningTree.add(new Arc(edge, true));
                    // Set the destination as discovered
                    discovered[destination] = true;
                    // Add the destination to the queue
                    queue.add(destination);
                }
            }
        }

        return spanningTree;
    }

    /*
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
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue
            source = queue.poll();

            // Look at every arc going out of that vertex
            for(Arc outgoingArcOfSource : graph.outAdjacency.get(source)) {
                // Get the destination of the current arc
                int destination = outgoingArcOfSource.edge.dest;
                // If that destination has not been discovered yet
                if(!discovered[destination]) {
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
    */
}


        /*
        // Queue the current node
        queue.add(source);

        // Index used to make sure we check every node
        int index = 0;

        // While there are still some nodes in the queue
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue
            source = queue.poll();

            // Look at every arc going out of that vertex
            for(Arc outgoingArcOfSource : graph.outAdjacency.get(source)) {
                // Get the destination of the current arc
                int destination = outgoingArcOfSource.edge.dest;
                // If that destination has not been discovered yet
                if(!discovered[destination]) {
                    // Add the arc to the spanning tree
                    spanningTree.add(outgoingArcOfSource);
                    // Set the destination and source as discovered
                    discovered[source] = true;
                    discovered[destination] = true;
                    // Add the destination to the queue
                    queue.add(destination);
                }
            }

            // If the queue is empty but we haven't check all the nodes yet
            if(queue.size() == 0) {
                for(; index < graph.order; index++) {
                    if(!discovered[index]) {
                        // Queue current node
                        queue.add(index);
                        index++;
                        break;
                    }
                }
            }
        }
        */