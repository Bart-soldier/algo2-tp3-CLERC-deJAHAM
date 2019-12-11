package src.spanningTree;

import java.util.ArrayList;
import java.util.LinkedList;

import src.graphTools.Arc;
import src.graphTools.Edge;
import src.graphTools.Graph;

/**
 * 3.1 Arbres couvrant de poids minimum aléatoire
 *
 * L'arbre obtenu n'est pas optimal étant donné qu'il n'existe que deux valeurs différentes comme poid (0 ou 1)
 */

public class MinimumSpanningTree {

    public static ArrayList<Arc> SpanningTree(Graph graph) {
        // For each vertex in the graph
        for(int vertex = 0 ; vertex < graph.order; vertex++) {
            // For each edge of that vertex
            for(Arc arc : graph.outAdjacency.get(vertex)) {
                // If the vertex is equal to the source of the edge (to avoid setting twice a random number)
                if(vertex == arc.edge.source) {
                    // Set weight to a random number between [0, 1]
                    arc.edge.weight = Math.random();
                }
            }
        }

        return primAlgorithm(graph);
    }

    // Prim's algorithm from a random source
    private static ArrayList<Arc> primAlgorithm(Graph graph)
    {
        // Random source
        int source = (int) (Math.random() * graph.order);

        // List of edges in the order of the node's discovery
        ArrayList<Arc> minimumSpanningTree = new ArrayList<>();

        // Mark all the vertices as not discovered
        boolean[] discovered = new boolean[graph.order];

        // Create a list for all of the edges going out of the cut
        ArrayList<Arc> outgoingEdgesOfCut = new ArrayList<>();

        // Mark the current node as discovered
        discovered[source] = true;
        // And add each edge connected to the current vertex to the outgoingEdgesOfCut
        outgoingEdgesOfCut.addAll(graph.outAdjacency.get(source));

        // While there are still some edges in the cut
        while (outgoingEdgesOfCut.size() != 0)
        {
            double currentEdgeWeight;
            double minWeight = 1000;
            Arc arc = null;


            // For each edge going out of the cut
            for(int i = 0; i < outgoingEdgesOfCut.size(); i++) {
                // If we have not yet discovered either the source of the destination of the edge
                if(!discovered[outgoingEdgesOfCut.get(i).edge.source] || !discovered[outgoingEdgesOfCut.get(i).edge.dest]) {
                    currentEdgeWeight = outgoingEdgesOfCut.get(i).edge.weight;
                    // If the current edge's weight is strictly smaller than the previously recorded minWeight
                    if(currentEdgeWeight < minWeight) {
                        // Then update the values
                        minWeight = currentEdgeWeight;
                        arc = outgoingEdgesOfCut.get(i);
                    }
                }
                // Otherwise, if we have discovered both, we can remove the edge from the list
                else {
                    outgoingEdgesOfCut.remove(i);
                    i--;
                }
            }

            // If you have found a minimum weight
            if(arc != null) {
                // If it was the source that was not discovered
                if(!discovered[arc.edge.source]) {
                    // You mark the source as discovered
                    discovered[arc.edge.source] = true;
                    // You add the source's edge to the outgoingEdgesOfCut
                    outgoingEdgesOfCut.addAll(graph.outAdjacency.get(arc.edge.source));
                }
                // If it was the destination that was not discovered
                else {
                    // You mark the destination as discovered
                    discovered[arc.edge.dest] = true;
                    // You add the destination's edge to the outgoingEdgesOfCut
                    outgoingEdgesOfCut.addAll(graph.outAdjacency.get(arc.edge.dest));
                }

                // And finally you add the edge to the minimum spanning tree
                minimumSpanningTree.add(arc);
            }
        }

        return minimumSpanningTree;
    }
}