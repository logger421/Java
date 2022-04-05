
public class Test {

	public static void main(String[] args) {
		System.out.println("DFS:");
		DFS_search g1 = new DFS_search();
		g1.readFile();
		g1.DFS(2);
		System.out.println();
		System.out.println("DFS END!");
		
		System.out.println("BFS:");
		BFS_search g2 = new BFS_search();
		g2.readFile();
		g2.BFS(2);
		System.out.println();
		System.out.println("BFS END!");
	}

}
