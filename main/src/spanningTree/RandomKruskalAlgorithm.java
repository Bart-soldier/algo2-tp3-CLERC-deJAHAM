package src.spanningTree;

import src.graphTools.Arc;
import src.graphTools.Edge;
import src.graphTools.Graph;

import java.util.ArrayList;

/**
 * 3.3 Insertion aléatoire d’arêtes
 */

public class RandomKruskalAlgorithm {

    public static ArrayList<Arc> SpanningTree(Graph graph) {
        // List of edges
        ArrayList<Arc> spanningTree = new ArrayList<>();

        // List of all edges in the graph
        ArrayList<Arc> graphEdges = new ArrayList<>();

        // For each vertex in the graph
        for(int vertex = 0 ; vertex < graph.order; vertex++) {
            // For each edge of that vertex
            for(Arc arc : graph.outAdjacency.get(vertex)) {
                // If the edge isn't already in the list
                if(!graphEdges.contains(arc)) {
                    // Add it to the list
                    graphEdges.add(arc);
                }
            }
        }

        // List of all edges in the graph in a random order
        ArrayList<Arc> randomEdges = new ArrayList<>();

        // Initialize randomEdges
        while(graphEdges.size() > 0) {
            // Take a random number between 0 and the size of graphEdges - 1
            int randomNumber = (int) (Math.random() * graphEdges.size());

            randomEdges.add(graphEdges.get(randomNumber));
            graphEdges.remove(randomNumber);
        }

        // In order to keep track of the predecessor of the edge.
        // If, when adding to the table, the edge does not have a predecessor, its predecessor is itself
        int[] predecessor = new int[graph.order];
        // Initialize predecessor
        for(int i = 0; i < graph.order; i++) {
            predecessor[i] = i;
        }

        // While we haven't look at all of the graph's edges
        while(randomEdges.size() > 0) {
            Arc arc = randomEdges.remove(0);

            // Get the farthest predecessor from the source
            int sourcePredecessor = parent(predecessor, arc.edge.source);
            // Get the farthest predecessor from the dest
            int destPredecessor = parent(predecessor, arc.edge.dest);

            // If the sourcePredecessor is the same as the destPredecessor, i.e. they converge towards the same vertex
            if(sourcePredecessor == destPredecessor){
                // Then we have a cycle so we do nothing
            }
            else {
                // Otherwise we don't have a cycle so we add the edge to the spanning tree
                spanningTree.add(arc);
                // We update the predecessor table
                int newSourcePredecessor = parent(predecessor, sourcePredecessor);
                int newDestPredecessor = parent(predecessor, destPredecessor);
                predecessor[newDestPredecessor] = newSourcePredecessor;
            }
        }

        return spanningTree;
    }

    private static int parent(int[] predecessor, int vertex){
        // Look for predecessor until you find one that has none and return it
        if(predecessor[vertex]!=vertex)
            return parent(predecessor, predecessor[vertex]);;
        return vertex;
    }
}
