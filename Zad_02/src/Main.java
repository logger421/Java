import java.util.ArrayList;
import java.util.List;

public class Main {
	
	
	public static void main(String[] args) {
		Compression c = new Compression();
		List<String> wejscie = new ArrayList<>(List.of("000","001","000","001","000","001","000","001","011","001","000","110","001","000","111","001","001","000","000","000","001"));
		for(String s : wejscie)
			c.addWord(s);
		c.compress();
		System.out.println(c.getHeader());
//		System.out.println();
//		List<String> wejscie2 = new ArrayList<>(List.of("0001", "0001", "0001", "0010", "0111", "0011", "0001", "0001", "0110", "0000", "0001", "0001", "0001","0001"));
//		Compression c2 = new Compression();
//		for(String s : wejscie2)
//			c2.addWord(s);
//		c2.compress();
		
	
	}
	
	
}
