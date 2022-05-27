import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MST {
    private int vertices;

    Set<Edge> mst_edges;
    PriorityQueue<Edge> all_edges;
    Vector<Integer> disjointsets;

    public MST(){
        mst_edges = new HashSet<Edge>();
        all_edges = new PriorityQueue<Edge>();
    }

    public int KurskalAlgorithm() {
        readFromFile();
        Edge current;
        while(!all_edges.isEmpty()) {
            current = all_edges.poll();
            if(willGenerateCycles(current))
                continue;
            mst_edges.add(current);
        }
        int suma = 0;
        for(var x : mst_edges)
            suma += x.weight;
        return suma;
    }
    private boolean willGenerateCycles(Edge e) {
        disjointsets = new Vector<>();
        initialiseList();
        if(disjointsets.get(e.start) == disjointsets.get(e.end))
            return true;
        else {
            int start = disjointsets.get(e.start);
            int end = disjointsets.get(e.end);
            int min = start > end ? end : start;
            int max = start > end ? start : end;
            for(int i = 0; i < disjointsets.size(); i++)
                if (disjointsets.get(i) == max)
                    disjointsets.set(i, min);
            return false;
        }
    }

    private void initialiseList() {
        for(int i = 0; i < vertices; i++)
            disjointsets.add(i);
    }

    public void readFromFile() {
        String path;

        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
        if(fileChooser.showOpenDialog(new JFileChooser()) == JFileChooser.APPROVE_OPTION ) {
            path = fileChooser.getSelectedFile().getAbsolutePath();
            try(Scanner sc = new Scanner(new File(path))) {
                vertices = sc.nextInt();
                while(sc.hasNext()) {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    int weight = sc.nextInt();
                    addEdge(x, y, weight);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("No file choosen!");
    }

    private void addEdge(int x, int y, int weight) {
        all_edges.add(new Edge(x, y, weight));
    }
}
