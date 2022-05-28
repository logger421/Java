import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MST {
    public List<Integer> visited;
    public List<Edge> MST;
    public List<List<Edge>> graph;
    public PriorityQueue<Edge>  edges_queue;

    private  int vertices = 0;

    public MST() {
        graph = new ArrayList<>();
        visited = new ArrayList<>();
        MST = new ArrayList<>();
        edges_queue = new PriorityQueue<>();
    }

    public void specifyNumberOfVertices(int n) {
        vertices = n;
        initialiseList();
    }

    public int primsAlgorithm() {
        int starting_p = 4;
        visited.add(starting_p);
        for(int i = 0; i < graph.get(starting_p).size(); i++)
            edges_queue.add(graph.get(starting_p).get(i));
        System.out.println(edges_queue);

        while(visited.size() < vertices) {
            Edge current = edges_queue.poll();
            if(visited.contains(current.end))
                continue;
            else {
                visited.add(current.end);
                MST.add(current);
                for(int i = 0; i < graph.get(current.start).size(); i++)
                    edges_queue.add(graph.get(current.start).get(i));
            }
        }

        int result = sum_of_edges_weight();
        return result;
    }
    private int sum_of_edges_weight() {
        int sum = 0;
        for(var x : MST)
            sum += x.weight;
        return sum;
    }

    public void readFile() {
        String path;
        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
        if(fileChooser.showOpenDialog(new JFileChooser()) == JFileChooser.APPROVE_OPTION ) {
            path = fileChooser.getSelectedFile().getAbsolutePath();
            try(Scanner sc = new Scanner(new File(path))) {
                vertices = sc.nextInt();
                specifyNumberOfVertices(vertices);
                while(sc.hasNext()) {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    int weight = sc.nextInt();
                    addEdge(x,y,weight);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("No file choosen!");
    }

    public void addEdge(int x, int y, int w) {
        Edge e = new Edge(x,y,w);
        graph.get(e.start).add(e);
    }

    private void initialiseList() {
        for(int i = 0; i < vertices; i++)
            graph.add(new ArrayList<Edge>());
    }
}

