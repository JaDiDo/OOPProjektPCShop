package products;
/**
* Subklasse von Produkt
* @author Jason Donner
*/
public class Maus extends Product{
	boolean bluetooth;
	/**
	 * Konstruktor
	 * @param alle Variablen von Superklasse und Subklasse
	 */
	public Maus(String marke, String modell, double preis, boolean bluetooth) {
		super(marke, modell, preis);
		this.bluetooth = bluetooth;
	}
}