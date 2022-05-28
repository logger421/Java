public class Test {
    public static void main(String[] args) {
        MST prims = new MST();
        prims.readFile();
        int weights_sum = prims.primsAlgorithm();
        System.out.println("Suma wag krawędzi: " + weights_sum);
    }
}
