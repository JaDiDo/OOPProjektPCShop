package main;
import productfactory.ProductFactory;
import products.Product;
/**
* Hauptklasse für Hauptmenü und Auswertung der Eingabe des menüs
* @author Jason Donner
*/
public class Mainshop {
	/**Fenster wird gzeichnet, Inhalt von Products.txt genommen falls vorhanden*/
		public static void main(String[] args) {
		GUI.draw();
		WritingFile.getFromSerializable();
		while(true)
			hauptMenue();
	}
	/**Displays Menü und öffnet jeweilige option je nach Eingabe*/
	private static void hauptMenue() { 
		GUI.updateText("-".repeat(57)
				+"<br>PC-Shop&nbsp;&nbsp;&nbsp;Hautmenü&nbsp;&nbsp;&nbsp;von: Jason Donner<br>"
				+"-".repeat(57)
				+"<br>1) Produkt anlegen<br>2) Produkt bearbeiten<br>3) Produkt suchen<br>4) Produkt löschen<br>0) Shop beenden<br>"
				+"-".repeat(57)
				+"<br>Bitte wählen:");
		switch(GUI.getUserinput()){
			case "1":
				produktAnlegen();
				break;
			case "2":
				produktBearbeiten();
				break;
			case "3":
				produktSuchen();
				break;
			case "4":
				produktLoeschen();
				break;
			case "0":
				shopBeenden();
				break;
			default:
				GUI.addWarningText("Fehlerhafte Eingabe");
		}
	}
	/**Neues Produkt erstellen für ArrayList allProducts*/
	private static void produktAnlegen() {
		GUI.updateText("Monitor, Motherboard, Tastatur oder Maus?");
		Product pf = ProductFactory.getProduct(GUI.getUserinput());
		//Kondition um falsche oder leere inputs die Erstellung des Produktes zu umgehen
		if( pf != null ) {
			LogicForArrayList.allProducts.add( pf );
			WritingFile.addToSerializable();
			if(LogicForArrayList.continueOrNot("Noch ein Produkt erstellen? (y/n)"))
				produktAnlegen();
		}
	}
	/**ein Produkt bearbeiten*/
	private static void produktBearbeiten() {
		if(LogicForArrayList.areThereProductsAndShow()) {
			GUI.updateText("Welches Produkt wollen sie bearbeiten?");
			String userinput = GUI.getUserinput();
			//-1 weil ArrayListe bei 0 startet und Reihenfolge mit 1
			if( userinput.matches("[0-9]+") && Integer.parseInt(userinput)-1 <= LogicForArrayList.allProducts.size() ){
				GUI.updateText("Mit Monitor, Motherboard, Tastatur oder Maus ersetzen");
				Product pf = ProductFactory.getProduct(GUI.getUserinput());
				//Kondition um falsche oder leere inputs beim erstellen die Erstellung zu umgehen
				if( pf != null ) {
					//-1 (siehe oben)
					LogicForArrayList.allProducts.set( Integer.parseInt(userinput)-1, pf );
					WritingFile.addToSerializable();
				if(LogicForArrayList.continueOrNot("Noch ein Produkt bearbeiten? (y/n)"))
						produktBearbeiten();
				}				
			}
			else {
				GUI.addWarningText("Fehlerhafte Eingabe");
			}
		}
		GUI.removeArrayList();
	}
	//TODO auch variablennamen durchgehen und nur text mit jeweiligen erwischt
	/**nach Produkt suchen*/
	private static void produktSuchen(){
		if(LogicForArrayList.areThereProductsAndShow()) {
			if( ! LogicForArrayList.findProdukt() )
				GUI.addWarningText("Kein Produkt hatte diesen Suchbegriff");
			if(LogicForArrayList.continueOrNot("Noch ein Produkt suchen? (y/n)"))
				produktSuchen();
		}
		GUI.removeArrayList();
	}
	/**ein Produkt löschen*/
	private static void produktLoeschen() {
		if(LogicForArrayList.areThereProductsAndShow()) {
			GUI.updateText("Welches Produkt soll gelöscht werden (Bitte Nummer eingeben)");
			String userinput = GUI.getUserinput();
			if( userinput.matches("[0-9]+") && 0 < Integer.parseInt(userinput) && Integer.parseInt(userinput) <= LogicForArrayList.allProducts.size() ){
				if( LogicForArrayList.continueOrNot("Soll es wirklich gelöscht werden (y/n)") )
					LogicForArrayList.allProducts.remove( Integer.parseInt(userinput)-1 );
				WritingFile.addToSerializable();
				if(LogicForArrayList.continueOrNot("Noch ein Produkt löschen? (y/n)"))
					produktLoeschen();
			}
			else
				GUI.addWarningText("Fehlerhafte Eingabe");
		}
		GUI.removeArrayList();
	}
	/**Abfragen, ob user Programm verlassen will*/
	private static void shopBeenden() {
		if( LogicForArrayList.continueOrNot("Soll wirklich beendet werden? (y/n)") ) {
			GUI.updateText("PC Shop wurde beendet");
			try {
				//um Schlussnachricht für einen Moment zu zeigen
				Thread.sleep(1000);
			}
			catch(Exception e) {}
			System.exit(0);
		}
	}
}