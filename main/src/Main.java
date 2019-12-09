package src;

import src.graphTools.Arc;
import src.graphTools.Edge;
import src.graphTools.Graph;
import src.spanningTree.*;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;


public class Main {

	@SuppressWarnings("unused")
	private final static Random gen = new Random();
	
	public static ArrayList<Edge> GenerateBFSSpanningTree(Graph graph) {
		ArrayList<Edge> randomTree = new ArrayList<>();
		
		// Non-random BFS
		ArrayList<Arc> BFSSpanningTree = BreadthFirstSearch.generateSpanningTree(graph, 0);

		for (Arc arc : BFSSpanningTree) randomTree.add(arc.edge);

		return randomTree;
	}
	
	
	public static void main(String argv[]) throws InterruptedException {


	    Graph graph = new Graph(8);
	    graph.addVertex(0);
	    graph.addVertex(1);
	    graph.addVertex(2);
	    graph.addVertex(3);
	    graph.addVertex(4);
	    graph.addVertex(5);
	    graph.addVertex(6);
	    graph.addVertex(7);
	    graph.addVertex(8);
	    graph.addEdge(new Edge(0, 1, 0));
	    graph.addEdge(new Edge(1, 2, 0));
	    graph.addEdge(new Edge(0, 3, 0));
	    graph.addEdge(new Edge(1, 4, 0));
	    graph.addEdge(new Edge(2, 5, 0));
	    graph.addEdge(new Edge(3, 4, 0));
	    graph.addEdge(new Edge(4, 5, 0));
	    graph.addEdge(new Edge(3, 6, 0));
	    graph.addEdge(new Edge(4, 7, 0));
	    graph.addEdge(new Edge(5, 8, 0));
	    graph.addEdge(new Edge(6, 7, 0));
	    graph.addEdge(new Edge(7, 8, 0));


		/*
		Graph graph = new Graph(4);
		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addEdge(new Edge(0, 1, 0));
		graph.addEdge(new Edge(1, 2, 0));
		graph.addEdge(new Edge(2, 3, 0));
		graph.addEdge(new Edge(3, 0, 0));
		graph.addEdge(new Edge(0, 2, 0));
		graph.addEdge(new Edge(1, 3, 0));
		*/


	    //ArrayList<Edge> testList = GenerateBFSSpanningTree(graph);
	    //ArrayList<Edge> testList = RandomBreadthFirstSearch.SpanningTree(graph);
	    //ArrayList<Edge> testList = MinimumSpanningTree.SpanningTree(graph);
	    //ArrayList<Edge> testList = RandomKruskalAlgorithm.SpanningTree(graph);
	    //ArrayList<Edge> testList = AldousBroderAlgorithm.SpanningTree(graph);
	    ArrayList<Edge> testList = WilsonAlgorithm.SpanningTree(graph);

		System.out.println("\nGraph :\n");
	    for (int i = 0 ; i < graph.order; i++) {
			for (Edge edge : graph.adjacency.get(i)) {
				System.out.println("Edge from " + edge.source + " to " + edge.dest + " with weight " + edge.weight);
			}
		}

		System.out.println("\nSpanning Tree :\n");
	    for(Edge edge : testList) {
            System.out.println("Edge from " + edge.source + " to " + edge.dest);
        }


	    /*
		Grid grid = null;
		grid = new Grid(1920/11,1080/11);


		Graph graph = grid.graph;
		
		//src.graphTools.Graph graph = new src.Complete(400).graph;
		
		//src.graphTools.Graph graph = new src.ErdosRenyi(1_000, 100).graph;

		//src.graphTools.Graph graph = new src.Lollipop(1_000).graph;
		
		int nbrOfSamples = 10;
		int diameterSum = 0;
		double eccentricitySum = 0;
		long wienerSum = 0;
		int degreesSum[] = {0, 0, 0, 0, 0};
		int degrees[];
		
		ArrayList<Edge> randomTree = null; 
		RootedTree rooted = null;

		long startingTime = System.nanoTime();
		for (int i = 0; i < nbrOfSamples; i++) {
			randomTree = GenerateBFSSpanningTree(graph);

			rooted = new RootedTree(randomTree,0);
			// rooted.printStats();
			diameterSum = diameterSum + rooted.getDiameter();
			eccentricitySum = eccentricitySum + rooted.getAverageEccentricity();
			wienerSum = wienerSum + rooted.getWienerIndex();
			
			degrees = rooted.getDegreeDistribution(4);
			for (int j = 1; j < 5; j++) {
				degreesSum[j] = degreesSum[j] + degrees[j];
			}
		}		
		long delay = System.nanoTime() - startingTime;
		
		System.out.println("On " + nbrOfSamples + " samples:");
		System.out.println("Average eccentricity: "
							+ (eccentricitySum / nbrOfSamples));
		System.out.println("Average wiener index: " 
							+ (wienerSum / nbrOfSamples));
		System.out.println("Average diameter: " 
							+ (diameterSum / nbrOfSamples));
		System.out.println("Average number of leaves: " 
							+ (degreesSum[1] / nbrOfSamples));
		System.out.println("Average number of degree 2 vertices: "
							+ (degreesSum[2] / nbrOfSamples));
		System.out.println("Average computation time: " 
							+ delay / (nbrOfSamples * 1_000_000) + "ms");
		
		
		if (grid != null) showGrid(grid,rooted,randomTree);
	}

	private static void showGrid(
			Grid grid, 
			RootedTree rooted, 
			ArrayList<Edge> randomTree
			) throws InterruptedException {
		JFrame window = new JFrame("solution");
		final Labyrinth laby = new Labyrinth(grid, rooted);

		laby.setStyleBalanced();
		// laby.setShapeBigNodes();
		// laby.setShapeSmallAndFull();
		laby.setShapeSmoothSmallNodes();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(laby);
		window.pack();
		window.setLocationRelativeTo(null);


		for (final Edge e : randomTree) {
					laby.addEdge(e);
		}
		laby.drawLabyrinth();

		window.setVisible(true);
		
		// Pour générer un fichier image.
		/*
		try {
			laby.saveImage("resources/random.png");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		*/
	}
}
