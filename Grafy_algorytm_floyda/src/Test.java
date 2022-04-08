public class Test {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.readFile();
        g.floyd_alogrithm();
        g.printFloydMatrix();
    }
}
