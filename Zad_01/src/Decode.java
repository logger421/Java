public class Decode extends DecoderInterface{
	
	String code = new String();
	int ones = 0;
	int x = 0;
	public void input(int bit) {
		int decoded = 0;
		// jeśli bit = 1 
		if ( bit == 1) {
			ones++;
		}
		// jeśli bit == 0 
		else if ( ones > 0 ){
			// jesli x sie nie zmienilo od poczatku programu przypisz wartosc jedynek
			if (x == 0) {
				x = ones;
				decoded = 0;
				code = code + decoded;
			}
			// jest x != 0 dekoduj bity
			else {
				if ( ones == x ) {
					decoded = 0;
					code = code + decoded;
				}
				else if ( ones == 2*x) {
					decoded = 1;
					code = code + decoded;
				}
				else if ( ones == 3*x ) {
					decoded = 2;
					code = code + decoded;
				}
				else if ( ones == 4*x ) {
					decoded = 3;
					code = code + decoded;
				}
			}
			// po zdekodowaniu sekwencji zresetuj liczbe jedynek
			ones = 0;
		}
	}
	
	public String output() {
		// jesli tylko nasz lancuch nie jest pusty zwroc go
		if (code.length() != 0)
			return code;
		else
			return "";
	}
	
	public void reset() {
		code = "";
		ones = 0;
		x = 0;	
	}

}
