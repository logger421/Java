import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph {
    private int vertices;
    private float[][] floydMatrix;
    private List<List<Node>> graph;

    public Graph() {}

    public Graph(int n) {
        vertices = n;
        initialiseMatrix();
        initialsieList();
    }

    public void specifyNumberOfVertices(int n) {
        vertices = n;
        initialiseMatrix();
        initialsieList();
    }

    public void addEdge(int vertex1, int vertex2, int weight) {
        floydMatrix[vertex1][vertex2] = weight;
        graph.get(vertex1).add(new Node(vertex2, weight));
    }

    public void floyd_alogrithm() {
        for(int k = 0; k < vertices; k++) {
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++ ) {
                    if(floydMatrix[i][j] > floydMatrix[i][k]+ floydMatrix[k][j])
                        floydMatrix[i][j] = floydMatrix[i][k]+ floydMatrix[k][j];
                }
            }
        }
        if(checkForCycles() == true)
            System.out.println("Graph ma cykle ujemne");
        else if(checkForCycles() == false)
            System.out.println("Graph nie ma cykli ujemnych");

    }

    public void printFloydMatrix() {
        for(int i = 0; i < vertices; i++) {
            for(int j = 0; j < vertices; j++) {
                System.out.print(floydMatrix[i][j] + " ");
            }
            System.out.println();
        }

        for(int i = 0; i < vertices; i++) {
            System.out.println("Koszt dotarcia z wierzchołka " + i + " do wierzchołka: ");
            for( int j = 0; j < vertices; j++ ) {
                System.out.println(j + " wynosi " + floydMatrix[i][j]);
            }
        }
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
                    addEdge(x, y, weight);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("No file choosen!");
    }

    private void initialiseMatrix() {
        floydMatrix = new float[vertices][vertices];
        for(int i = 0; i< vertices; i++) {
            for(int j = 0; j < vertices; j++) {
                if(i == j)
                    floydMatrix[i][j] = 0;
                else
                    floydMatrix[i][j] = Float.POSITIVE_INFINITY;
            }
        }
    }

    private void initialsieList() {
        graph = new ArrayList<>();
        for(int i = 0; i < vertices; i++)
            graph.add(new ArrayList<>());
    }

    private boolean checkForCycles() {
        for(int i = 0; i < vertices; i++)
            for(int j = 0; j < vertices; j++)
                if((i == j) && floydMatrix[i][j] < 0 )
                    return true;
        return false;
    }

    private static class Node {
        final int value;
        final int weight;

        public Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return value + "(" + weight + ") ";
        }
    }
}
