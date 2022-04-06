
public class Edge {
	
	final int vertex1;
	final int vertex2;
	final int weight;
	
	public Edge(int vertex1, int vertex2, int val) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = val;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + this.vertex1 + "," + this.vertex2 + ", "+ this.weight + ")";
	}
	
}

