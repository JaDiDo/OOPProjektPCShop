package products;
/**
* Subklasse von Produkt
* @author Jason Donner
*/
public class Motherboard extends Product{
	double taktfrequenz;
	/**
	 * Konstruktor
	 * @param alle Variablen von Superklasse und Subklasse
	 */
	public Motherboard(String marke, String modell, double preis, double taktfrequenz) {
		super(marke, modell, preis);
		this.taktfrequenz = taktfrequenz;
	}	
}