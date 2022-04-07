import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        System.out.println("Welcome!\n");
        Graph_Topological_Sort g1 = new Graph_Topological_Sort();
        g1.specifyNumberOfVertices(5);
        g1.addEdge(0,3, 1);
        g1.addEdge(0,1, 1);
        g1.addEdge(1,2, 1);
        g1.addEdge(3,1, 1);
        g1.addEdge(4,2, 1);
        g1.printGraph();
        g1.sort_topo();

    }
}
