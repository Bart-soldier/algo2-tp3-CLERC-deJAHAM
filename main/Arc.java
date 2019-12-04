
public class Arc {
	Edge edge;
	boolean reversed;
	
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
