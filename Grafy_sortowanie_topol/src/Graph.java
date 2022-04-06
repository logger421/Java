import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Graph {
	private int vertices;
	List<List<Node>> list_of_neighbours = new ArrayList<>();
	
	public Graph() {
		vertices = 0;
	}
	
	public Graph(int vertices) {
		for(int i  = 0; i < vertices; i++)
			list_of_neighbours.add(i, new ArrayList<>());
	}
	
	public Graph(List<Edge> edges) {
		for(int i  = 0; i < edges.size(); i++)
			list_of_neighbours.add(i, new ArrayList<>());
		
		for(Edge e : edges)
			list_of_neighbours.get(e.vertex1).add(new Node(e.vertex2, e.weight));
	}
	
	public void specifyNumberOfVertices(int n) {
		vertices = n;
		for(int i  = 0; i < vertices; i++)
			list_of_neighbours.add(i, new ArrayList<>());
	}
	
	public void addEdge(int vertex1, int vertex2, int weight) {
		list_of_neighbours.get(vertex1).add(new Node(vertex2, weight));
	}
	
	public void printGraph() {
		
		int src_vertex = 0;
        int list_size = list_of_neighbours.size();
        System.out.println("Full Graph: ");
        
        while (src_vertex < list_size) {
        	System.out.print("Vertex:" + src_vertex);
            for (Node edge : list_of_neighbours.get(src_vertex)) {
                System.out.print(" ==> " + edge);
            }
            System.out.println();
            src_vertex++;
        }
	}
	
	public void printNeighbourIndices(int idx) {
		System.out.print("Neigbour indices of " + idx);
		for(Node n : list_of_neighbours.get(idx))
			System.out.print("->" + n);
		System.out.println();
	}
	public void readGraphFromFile() {
		String path = "";
		
		JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
		if(fileChooser.showOpenDialog(new JFileChooser()) == JFileChooser.APPROVE_OPTION )
			path = fileChooser.getSelectedFile().getAbsolutePath();
		
		try(Scanner sc = new Scanner(new File(path));) {
			vertices = sc.nextInt();
			specifyNumberOfVertices(vertices);
			while(sc.hasNext()) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int weight = sc.nextInt();
				addEdge(x, y, weight);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class Node {
		final int value;
		final int weight;
		public Node(int value, int weight) {
			this.value = value;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return value + "(" + weight + ") ";
		}
	}
}
