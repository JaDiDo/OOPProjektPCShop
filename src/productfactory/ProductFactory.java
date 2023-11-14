package productfactory;
import java.util.Arrays;
import static main.GUI.*;
import products.*;
import static productfactory.SpecialExceptions.*;
/**
* factory class -> um die Subklassen des Produktes zu erstellen und Fehler bei
* 				   Eingabe zu finden 
* @author Jason Donner
*/

public class ProductFactory {
	
	//updatetext, addWarningText und getUserinput wurden von main.GUI importiert
	//subklassen im Package products
	
	/**verwendet Benutzereingabe, um Produkte zu erstellen
	 * @return Subklasse von Produkt, falls erfolgreich, sonst null
	 * @throws ExceptionEmpyString
	 * 		wenn Eingabe leer ist
	 * @throws ExceptionFalseType
	 * 		im Fall, dass die Eingabe nicht in den benötigten Datentyp umgewandelt werden kann
	*/
	public static Product getProduct(String type){
		String marke, modell; 
		double preis;
		try {
			if(Arrays.asList(new String[] {"maus","monitor","motherboard","tastatur"}).contains(type.toLowerCase())){
				updateText("Bitte Marke eingeben: ");
				marke = userinputWithCheck(getUserinput());
				updateText("Bitte Modell eingeben: ");
				modell = userinputWithCheck(getUserinput());
				updateText("Bitte Preis eingeben: ");
				preis = transformToDouble(getUserinput());
				if( type.equalsIgnoreCase("Maus")) { 
					updateText("Hat es Bluetooth (y/n)?: ");
					boolean bluetooth = transformToBoolean(getUserinput());
					return new Maus(marke, modell, preis, bluetooth);
				}
				else if( type.equalsIgnoreCase("Monitor")) {
					updateText("Bitte Resolution eingeben: ");
					String resolution = userinputWithCheck(getUserinput());
					updateText("Hat es Höhenverstellung (y/n)?: ");
					boolean hoehenverstellung = transformToBoolean(getUserinput());
					return new Monitor(marke, modell, preis, resolution, hoehenverstellung);
				}
				else if( type.equalsIgnoreCase("Motherboard")) { 
					updateText("Bitte Taktfrequenz eingeben: ");
					double taktfrequenz= transformToDouble(getUserinput());
					return new Motherboard(marke, modell, preis, taktfrequenz);
				}
				else if( type.equalsIgnoreCase("Tastatur")) {
					updateText("Hat es LED (y/n)?: ");
					boolean led = transformToBoolean(getUserinput());
					return new Tastatur(marke, modell, preis, led);
				}
			}
			else
				addWarningText("Kein solches Produkt gefunden");
		}
		//custom exceptions importiert von SpecialExceptions
		
		catch (Exception e) {
			String defaulttext = "Produkt konnte aufgrund ";
			if(e instanceof ExceptionEmptyString)
				addWarningText(defaulttext + "leerer Eingabewerte nicht gespeichert werden.");
			else if(e instanceof ExceptionFalseType)
				addWarningText(defaulttext + "Eingabewerte mit falschem Datentyp nicht gespeichert werden.");
			else
				addWarningText(defaulttext + "unbekannten Gründen nicht gespeichert werden");
		}
		return null;
	}	
	/**checkt Eingabe, error falls leerer String
	 * @param Stringeingabe des Users
	 * @return selber String, falls erfolgreich
	 * @throws leerer String
	 * */
	private static String userinputWithCheck(String userinput) throws ExceptionEmptyString {
		if( ! userinput.equals("") )
			return userinput;
		throw new ExceptionEmptyString();
	}
	/**wandelt Eingabe in double, error falls nicht möglich
	 * @param Stringeingabe des Users
	 * @return Eingabe als double umgewandelt
	 * @throws nicht möglich 
	 * */
	private static double transformToDouble(String userinput) throws ExceptionFalseType{
		userinput = userinput.replace(",", ".");
		if( userinput.matches("[+-]?\\d*.?\\d+"))
			return Double.parseDouble(userinput);
		throw new ExceptionFalseType();
	}
	/**wandelt Eingabe in boolean, error falls nicht möglich
	 * mögliche Eingaben: y n ja nein 1 0
	 * @param Stringeingabe des Users
	 * @return Eingabe als boolean umgewandelt
	 * @throws nicht möglich 
	 * */
	private static boolean transformToBoolean(String userinput) throws ExceptionFalseType{
		if( userinput.toLowerCase().matches("([y1]||ja)"))
			return true;
		if( userinput.toLowerCase().matches("([n0]||nein)"))
			return false;
		throw new ExceptionFalseType();
	}
}
