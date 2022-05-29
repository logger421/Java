import java.util.List;

public class Test {
    public static void main(String[] args) {
        MST prims = new MST();
        prims.readFile();
        int weights_sum = prims.primsAlgorithm();
        System.out.println("Suma wag krawędzi: " + weights_sum);
        showVisitedList(prims.visited, 4);
    }
    public static void showVisitedList(List<Integer> visited, int n) {
        System.out.println("Kolejność dodawania wierzchołków do listy zaczynająć od indeksu " + n + " ");
        System.out.print(visited);
    }
}
