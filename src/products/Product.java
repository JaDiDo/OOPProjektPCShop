package products;
import java.io.Serializable;
import java.lang.reflect.Field;

import main.GUI;
/**
* Superklasse für gleiche Variablen und zum Suchen oder der Ausgabe aller Variablen
* @author Jason Donner
*/
public abstract class Product implements Serializable{
	private String marke, modell;
	private double preis;
	public Product(String marke, String modell, double preis) {
		this.marke = marke;
		this.modell = modell;
		this.preis = preis;
	}
	/**geht durch alle Namen und Variablen der Produkte um zu sehen, ob es vorhanden ist
	 * @param der Suchbegriff
	 * @return true wenn suchbegriff vorhanden
	 * */
	public boolean goThroughAllVariables(String suchbegriff){
		//regular expression, start für case insensitive 
		suchbegriff = "(?i).*"+suchbegriff+".*";
		Field[] allVariables = this.getClass().getDeclaredFields();
		Field[] allVariablesSuper = this.getClass().getSuperclass().getDeclaredFields();
		try {
			for(Field item : allVariables) {
				if(item.get(this).toString().matches(suchbegriff))
					return true;
			}
			for(Field item : allVariablesSuper) {
				if(item.get(this).toString().matches(suchbegriff))
					return true;
			}
			if( this.getClass().getSimpleName().matches(suchbegriff) )
				return true;
		}catch(Exception e) {
			GUI.addWarningText("Fehler beim durchsuchen der Produkte");
		}
		return false;
	}
	/**um alle Produkt auszugegeben mit deren Inhalt
	 * @return komplette ArrayList+Inhalt formatiert
	 */
	@Override
	public String toString() {
			return String.format( this.getClass().getSimpleName()+" -> marke: %s; modell: %s; preis %s"+
					fieldToString(0)
						, marke, modell, preis);
	}
	/**gibt alle Variablen des Fields aus
	 * @param counter, damit nicht redundante Methode endlos ist
	 * @return Die jeweiligen Variablen der Subklassen
	 * */ 
	private String fieldToString(int counter){
		try {
			if( counter < (this.getClass().getDeclaredFields().length) ){
				return String.format("; " + this.getClass().getDeclaredFields()[counter].getName()
									+ ": " + this.getClass().getDeclaredFields()[counter].get(this) ) 
									+ fieldToString(counter + 1);
			}
			return "";
		}
		catch (Exception e) {
			GUI.addWarningText("Fehler beim Holen der Informationen");
			return "";
		}
	}
	
}
