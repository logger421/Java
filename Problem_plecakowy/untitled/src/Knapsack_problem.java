import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack_problem {
    private int backpack_size = 24;
    private int[][] matrix;
    private List<Pair> przedmioty = new ArrayList<>();

    public Knapsack_problem() {
        readFromFile();
        matrix = new int[przedmioty.size() + 1][backpack_size + 1];
        main_algorithm();
    }

    private void main_algorithm() {
        for(int j = 0; j < backpack_size + 1; j++)
            matrix[0][j] = 0;

    }
    private void readFromFile() {
        String path;
        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));

        if(fileChooser.showOpenDialog(new JFileChooser()) == JFileChooser.APPROVE_OPTION ) {
            path = fileChooser.getSelectedFile().getAbsolutePath();

            try(Scanner sc = new Scanner(new File(path))) {

                while(sc.hasNext()) {
                    przedmioty.add(new Pair(sc.nextInt(), sc.nextInt()));
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("No file choosen!");
    }

    class Pair {
        int weight;
        int value;
        public Pair(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
