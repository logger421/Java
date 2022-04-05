import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Graph {
	protected List<Integer> graph[];
	protected int number_of_vertices;
	
	public Graph() {
		number_of_vertices = 0;
	}
	
	public Graph(int ile) {
		// TODO Auto-generated constructor stub
		number_of_vertices = ile;
		graph = new ArrayList [number_of_vertices];
		
		for(int i = 0; i < number_of_vertices; i++)
			graph[i] = new ArrayList<>();
		
	}
	
	public void addEdge(int vx1, int vx2) {
		graph[vx1].add(vx2);
	}
	
	public void specifyNumberOfVertices(int vertices) {
		number_of_vertices = vertices;
		graph = new ArrayList [number_of_vertices];
		
		for(int i = 0; i < number_of_vertices; i++)
			graph[i] = new ArrayList<>();
	}
	
	public void printNeigboursIndices(int idx) {
		System.out.print(idx + ")-> ");
		for(int i : graph[idx])
			System.out.print(i + " ");
		System.out.println();
	}
	
	public void readFile() {
		String path = "";
		JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
		if(fileChooser.showOpenDialog(new JFileChooser()) == JFileChooser.APPROVE_OPTION )
			path = fileChooser.getSelectedFile().getAbsolutePath();
		
		try(Scanner sc = new Scanner(new File(path));) {
			int vertices = sc.nextInt();
			specifyNumberOfVertices(vertices);
			while(sc.hasNext()) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				addEdge(x, y);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
