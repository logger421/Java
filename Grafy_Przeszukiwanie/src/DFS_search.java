import java.util.Comparator;
import java.util.Iterator;

public class DFS_search extends Graph{

	public void DFS(int vertex) {
		boolean visited_vertices[] = new boolean[number_of_vertices];
		sortListsOfNeigbours();
		DFS_recursion(vertex, visited_vertices);
		System.out.println();
	}

	private void DFS_recursion(int vertex, boolean visited_vertices[]) {
		visited_vertices[vertex] = true;
		System.out.print(vertex + " ");
		Iterator<Integer> it = graph[vertex].listIterator();
		while(it.hasNext()) {
			int node = it.next();
			if(!visited_vertices[node])
				DFS_recursion(node, visited_vertices);
		}
	}

	private void sortListsOfNeigbours() {
		for(int i = 0; i < number_of_vertices; i++) {
			graph[i].sort(Comparator.naturalOrder());
		}
	}
}
