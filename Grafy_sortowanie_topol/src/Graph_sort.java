import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Graph_sort {
    private int vertices;
    private List< List<Integer> > graph = new ArrayList<>();
    private List< List<Integer> > list_of_edges_incoming= new ArrayList<>();
    public List<Integer> result = new ArrayList<>();
    LinkedList<Integer> no_incoming_edges_indices;

    public Graph_sort() { }

    public Graph_sort(int n) {
        vertices = n;
        for(int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
            list_of_edges_incoming.add(new ArrayList<>());
        }
    }

    public void specifyNumberOfVertices(int n) {
        vertices = n;
        for(int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
            list_of_edges_incoming.add(new ArrayList<>());
        }
    }

    public void addEdge(int vertex1, int vertex2) {
        graph.get(vertex1).add(vertex2);
        list_of_edges_incoming.get(vertex2).add(vertex1);
    }

    public void readFromFile() {
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
                    addEdge(x, y);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("No file choosen!");
    }

    public void sort() {
        no_incoming_edges_indices = new LinkedList<>();
        boolean[] visited = new boolean[vertices];
        Integer[] incomingEdges = new Integer[vertices];

        initialiseVisited(visited);
        initialiseIncoming(incomingEdges);
        addIndicesToQueue();
        int current;

        while(!no_incoming_edges_indices.isEmpty()) {
            current = no_incoming_edges_indices.pop();
            visited[current] = true;
            result.add(current);
            for(Integer el : graph.get(current)) {
                incomingEdges[el]--;
                if(incomingEdges[el] == 0 && (!visited[el]))
                    no_incoming_edges_indices.add(el);
            }
        }
        if(result.size() == vertices)
            System.out.println("Topological sorting succeeded! " + result);
        else
            System.out.println("Graph is not acyclist!");
    }

    private void initialiseIncoming(Integer[] arr) {
        for(int i = 0; i < vertices; i++) {
            arr[i] = list_of_edges_incoming.get(i).size();
        }
    }
    private void initialiseVisited( boolean[] arr) {
        for(boolean b : arr)
            b = false;
    }
    private void addIndicesToQueue() {
        for(int i = 0; i < vertices; i++)
            if(list_of_edges_incoming.get(i).size() == 0)
                no_incoming_edges_indices.add(i);
    }
}

