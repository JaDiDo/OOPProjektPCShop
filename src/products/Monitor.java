package products;
/**
* Subklasse von Produkt
* @author Jason Donner
*/
public class Monitor extends Product{
	//2 um zu sehen ob Methode fieldToString in Produkts funktioniert
	String resolution;
	boolean hoehenverstellung;
	/**
	 * Konstruktor
	 * @param alle Variablen von Superklasse und Subklasse
	 */
	public Monitor(String marke, String modell, double preis, String resolution, boolean hoehenverstellung) {
		super(marke, modell, preis);
		this.resolution = resolution;
		this.hoehenverstellung = hoehenverstellung;
	}
}