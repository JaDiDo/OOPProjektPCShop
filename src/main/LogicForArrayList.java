package main;
import java.util.ArrayList;
/**
 * zum Hinzufügen/Entfernen von Produkten in einer ArrayList, suchen innerhalb dieser
 *oder ob der benutzer verlassen möchte
* @author Jason Donner
*/
abstract class LogicForArrayList {
	static ArrayList <products.Product> allProducts = new ArrayList<products.Product>();
	private static String userinput;
	/**um ArrayList auszugeben
	 * falls keine vorhanden geh zurück zum Menü
	 * @return ob mindestens ein Produkt vorhanden ist
	 * */
	static boolean areThereProductsAndShow(){
		if( allProducts.size() == 0) {
			GUI.addWarningText("Keine Produkte vorhanden");
			return false;
		}
		else {
			GUI.showArrayList();
			for (int i = 0; i < allProducts.size(); i++) 
				GUI.addToArrayList((i+1)+": "+allProducts.get(i));
			return true;
		}
	}
	/**Um ein Suchbegriff der Eingabe zu verwenden und
	 *  durch die Produkte mit Variablen-/Klassennamen zu gehen
	 *  @return mindestens ein Produkt wurde gefunden
	 *  */
	static boolean findProdukt() {
		GUI.updateText("Suchbegriff eingeben:");
		userinput = GUI.getUserinput();
		boolean AProduktHasBeenFound = false;
		GUI.updateText("Folgende Produkte haben den Suchbegriff:");
		GUI.showArrayList();
		for (int i = 0; i < allProducts.size(); i++) {
			if(allProducts.get(i).goThroughAllVariables(userinput)) {
				GUI.addToArrayList((i+1)+": "+allProducts.get(i));
				AProduktHasBeenFound = true;
				continue;
			}
		}
		return AProduktHasBeenFound;
	}
	/**nachfragen, ob das Programm geschlossen werden soll
	 * @return ob verlassen werden soll
	 * */
	static boolean continueOrNot(String questionForUser) {
		GUI.updateText(questionForUser);
		userinput = GUI.getUserinput();
		if(  userinput.equals("y") || userinput.equals("ja"))
			return true;
		if( ! (userinput.equals("n") ||  userinput.equals("nein")) )
			GUI.addWarningText("Fehlerhafte eingabe");
		return false;	
	}
}