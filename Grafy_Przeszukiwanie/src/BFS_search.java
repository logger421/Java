import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class BFS_search extends Graph{

	public void BFS(int vertex) {
		sortListsOfNeigbours();
		boolean visited[] = new boolean[number_of_vertices];
		LinkedList<Integer> queue = new LinkedList<>();
		visited[vertex] = true;
		queue.add(vertex);

		int current_node;
		while(!queue.isEmpty()) {
			current_node = queue.pop();
			System.out.print(current_node + " ");

			Iterator<Integer> it = graph[current_node].listIterator();
			while(it.hasNext()) {
				int next_node = it.next();
				if(!visited[next_node]) {
					visited[next_node] = true;
					queue.add(next_node);
				}
			}
		}
	}

	private void sortListsOfNeigbours() {
		for(int i = 0; i < number_of_vertices; i++) {
			graph[i].sort(Comparator.naturalOrder());
		}
	}
}
