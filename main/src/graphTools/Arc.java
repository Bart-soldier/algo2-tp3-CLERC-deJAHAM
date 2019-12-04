package src.graphTools;

public class Arc {
	public Edge edge;
	public boolean reversed;
	
	public Arc(Edge e, boolean reversed) {
		this.edge = e;
		this.reversed = reversed;
	}
	
	public int getSource() {
		return (reversed ? edge.getDest() : edge.getSource());
	}
	
	public int getDest() {
		return (reversed ? edge.getSource() : edge.getDest());
	}
	
}
