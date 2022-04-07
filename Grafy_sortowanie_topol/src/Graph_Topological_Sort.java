import java.util.*;

public class Graph_Topological_Sort extends Graph{
    Stack<Integer> sorted;
    boolean[] visited;

    public void sort_topo() {
        Stack<Integer> sorted = new Stack<>();
        visited = new boolean[vertices];
        initialiseVisitedArray();
        for(int i = 0; i < vertices; i++) {
            if(!visited[i])
                recursive_sort(i);
        }
        printStack();
    }

    private void recursive_sort(int v) {
        visited[v] = true;
        int i;
        Iterator<Node> it = list_of_neighbours.get(v).listIterator();
        System.out.println(v + " " + list_of_neighbours.get(v));
        while(it.hasNext()) {
            i = it.next().value;
            if(!visited[i])
                recursive_sort(i);
        }
        sorted.push(v);
    }

    private void initialiseVisitedArray() {
        for(boolean v : visited)
            v = false;
    }
    private void printStack() {
        System.out.println("Topological sort result:");
        while(!sorted.empty())
            System.out.print(sorted.pop() + " ");
    }
}
