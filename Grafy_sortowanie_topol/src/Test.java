import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        System.out.println("Welcome!\n");
        Graph_sort g1 = new Graph_sort();
        g1.readFromFile();
        g1.sort();
        System.out.println("CYA!");
    }
}
