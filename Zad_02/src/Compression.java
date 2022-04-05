import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Compression implements CompressionInterface {
	
	Map<String, String> header = new HashMap<String, String>();
	Map<String, String> reverseHeader = new HashMap<String, String>();

	List<String> slowaWejsciowe = new ArrayList<>();
	
	Map<String, Integer> liczbaWystapien = new HashMap<>();
	Map<String, Integer> posortowanaLiczbaWystapien = new LinkedHashMap<>();
	
	private int dlugoscSlowWejsciowych = 0;
	
	@Override
	public void addWord(String word) {
		// TODO Auto-generated method stub
		slowaWejsciowe.add(word);
		dlugoscSlowWejsciowych += word.length();
		addOccurence(word);
		
	}
	
	private void addOccurence(String word) {
		if(liczbaWystapien.containsKey(word)) {
			liczbaWystapien.put(word, liczbaWystapien.get(word) + 1);
		}
		else {
			liczbaWystapien.put(word, 1);
		}
	}
	
	@Override
	public void compress() {
		sortByValue(liczbaWystapien);
		//System.out.println(slowaWejsciowe);
		//System.out.println("posortowana Mapa" + posortowanaLiczbaWystapien);
		List<String> slowaDoKompresji = new ArrayList<>(posortowanaLiczbaWystapien.keySet());
		//System.out.println("slowaDoKompresji" + slowaDoKompresji);
		
		List<String> slowaSkompresowane = new ArrayList<>();
		int klucz = znajdzDlugoscKlucza(slowaDoKompresji, dlugoscSlowWejsciowych, slowaSkompresowane);
		//System.out.println("Skompresowane Słowa: " + slowaSkompresowane);
		int iloscSlow = slowaSkompresowane.size();
		List<String> klucze = utworzKlucz(klucz, iloscSlow);
		utworzNaglowek(slowaSkompresowane, klucze);
		utworzOdwrotnyNaglowek();
		//System.out.println("Reverse: " + reverseHeader);
	}
	
	/*
	 * Sortuje przekazana mape po wartosciach kluczy
	 */
	private void sortByValue(Map<String, Integer> liczbaWystapien) {
		liczbaWystapien.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.forEachOrdered(x -> posortowanaLiczbaWystapien.put(x.getKey(), x.getValue()));
	}
	
	/*
	 * Czuka dlugosci klucza 
	 */
	private int znajdzDlugoscKlucza(List<String> slowaDoKompresji, int poczatkowaOptymalna, List<String> slowaSkompresowane) {
		
		int minDlugoscKlucza = 1;
		int maksDlugoscKlucza = slowaDoKompresji.get(0).length();
		int optymalna = 0;
		
		List<String> uzyteDoSzyfrowaniaTemp = new ArrayList<>();
		
		// dla ilosci bitow mniejszej od dlugosci slowa wejsciowego kompresja ma sens
		for(int i = 1; i < maksDlugoscKlucza; i++) {
			int ileBitowZmiesciKlucz = (int) java.lang.Math.pow(2, i - 1);
			// ile można utworzyć wyrazów z klucza, dla dlugosci 1 -> 0, dla dlugosci 2 -> 00,01, etc...
			for(int j = 1; j <= ileBitowZmiesciKlucz; j++) {	
				int zysk = 0;
				int strata = 0; 
				uzyteDoSzyfrowaniaTemp.clear(); // wyczysc liste "podejrzanych" slow
				
				int bits = 0;
				for(String slowo : slowaDoKompresji) {
					int liczbaPowtorzenSlowa = liczbaWystapien.get(slowo);
					if (bits < j) {
						zysk += (slowo.length() - i) * liczbaPowtorzenSlowa; 
						strata += slowo.length() + i; // co dodajemy do slownika
					}
					else {
						strata += 1 * liczbaPowtorzenSlowa;
					}
					bits++;
				}
				if(optymalna < (zysk - strata) ) {
					
					optymalna = zysk-strata;
					minDlugoscKlucza = i;
					
					for(int l = 0; l < j; l++)
						uzyteDoSzyfrowaniaTemp.add(slowaDoKompresji.get(l));
					
					slowaSkompresowane.clear(); // wyczysc liste -> znaleziono lepsze slowa do komprejsi
					slowaSkompresowane.addAll(uzyteDoSzyfrowaniaTemp);
				}
			}
		}
		return minDlugoscKlucza;
	}
	
	private List<String> utworzKlucz(int dlugoscKlucza, int iloscSlow) {
		List<String> klucze = new ArrayList<>();
		for (int i = 0; i  < iloscSlow; i++) {
			klucze.add( String.format("%" + String.valueOf(dlugoscKlucza) + "s", Integer.toBinaryString(i)).replaceAll(" ", "0") );
		}
		return klucze;
	}
	
	private void utworzNaglowek(List<String> skompresowane, List<String> klucze) {
		for (int i = 0; i < skompresowane.size(); i++) {
			header.put(klucze.get(i), skompresowane.get(i));
		}
	}
	
	private void utworzOdwrotnyNaglowek() {
		for(var it : header.entrySet())
			reverseHeader.put(it.getValue(), it.getKey());
	}
	
	@Override
	public Map<String, String> getHeader() {
		// TODO Auto-generated method stub
		return header;
	}

	@Override
	public String getWord() {
		String slowo = slowaWejsciowe.get(0);
		slowaWejsciowe.remove(0);
		if(reverseHeader.isEmpty()) {
			return slowo;
		}
		else {
			if(reverseHeader.containsKey(slowo)) {
				return reverseHeader.get(slowo);
			}
			else {
				return "1" + slowo;
			}
		}
	}
}
