package products;
/**
* Subklasse von Produkt
* @author Jason Donner
*/
public class Tastatur extends Product{
	boolean led;
	/**
	 * Konstruktor
	 * @param alle Variablen von Superklasse und Subklasse
	 */
	public Tastatur(String marke, String modell, double preis, boolean led) {
		super(marke, modell, preis);
		this.led = led;
	}
}