package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Packt die ArrayList in eine (read-only) Textfile: ".Products.txt"
 * @author Jason Donner 
 */
public class WritingFile implements Serializable{
	private static File file = new File(".Products.txt");
	/**um Daten des Textfiles zu verändern*/
    static void addToSerializable(){
    	resetSerializable();
    	//true for append
    	try (FileOutputStream fos = new FileOutputStream(file, true);
    			ObjectOutputStream oos = new ObjectOutputStream(fos);){
			oos.writeObject(LogicForArrayList.allProducts);
    	}catch (Exception e) {}
    	finally {file.setReadOnly();}
	}
    /**um Daten der Textfile zu erhalten*/
    static void getFromSerializable(){
    	try (FileInputStream fis = new FileInputStream(file);
    			ObjectInputStream ois = new ObjectInputStream(fis);){
	    	LogicForArrayList.allProducts = (ArrayList<products.Product>) ois.readObject();
    	}
	    catch (Exception e) {
	    		//falls Korrupte/keine Datei, erstelle eine neue
	    	resetSerializable();
	    	file.setReadOnly();
	    }
	}
    /**um eine Textdatei zu erweitern oder zurückzusetzen*/
    static void resetSerializable(){
    	file.setWritable(true);
    	try (PrintWriter writer = new PrintWriter(file);){
    		writer.print("");
    	}
    	catch (Exception e) {}
    }
}