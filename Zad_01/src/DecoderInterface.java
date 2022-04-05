public abstract class DecoderInterface {
	/**
	 * Metoda pozwala na dostarczanie danych do zdekodowania. Pojedyncze wywoĹanie
	 * metody dostarcza jeden bit.
	 * 
	 * @param bit Argumentem wywoĹania jest dekodowany bit. Argument moĹźe przybraÄ
	 *            wartoĹci wyĹÄcznie 0 i 1.
	 */
	public abstract void input(int bit);

	/**
	 * Metoda zwraca odkodowane dane. Metoda nigdy nie zwraca null. 
	 * Jeśli jeszcze żadna liczba nie została odkodowana metoda zwraca "" (pusty ciąg znaków, 
	 * czyli ciąg znaków o długości równej 0).
	 * @return CiÄg znakĂłw reprezentujÄcy sekwencjÄ odkodowanych danych.
	 */
	public abstract String output();

	/**
	 * Metoda przywraca stan początkowy. Proces odkodowywania danych zaczyna się od
	 * początku.
	 */
	public abstract void reset();
}
