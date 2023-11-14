package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * zeigt eine Oberfläche für Menü und Eingabe des Users
 * @author Jason Donner
 */
public abstract class GUI {
	private static JPanel mainPanel = new JPanel();
	//feedback für user
	private static JLabel label=new JLabel("", SwingConstants.CENTER);
	private static String addWarning = "", userinput;
	
	//zum Zeigen der Produkte
	private static JTextArea textArea = new JTextArea();
	private static JScrollPane pane = new JScrollPane(textArea);
	
	//Eingabefeld des Users
	private static JTextField txtUser = new JTextField();
	/**
	 * baut die Hauptstruktur der Oberfläche auf
	 */
	static void draw() {
		//Einstellung für unteren Abschnitt
		JPanel southpanel = new JPanel(); 
	    txtUser.setPreferredSize(new Dimension(50,25));
	    southpanel.add(txtUser);
	    JButton button = new JButton("Bestätigen");
	    southpanel.add(button);
	    ActionListener myActionListener = new ActionListener() {
	    	/**um Enter des Textfields oder Button zu bekommen
	    	 * -> aktualiziere dann den Output
	    	 * */
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		userinput = txtUser.getText();
	    		txtUser.setText("");
	    	}
	    };
	    txtUser.addActionListener(myActionListener);
	    button.addActionListener(myActionListener);
	    //einstellung für oberen Abschnitt
	    mainPanel.setLayout(new BoxLayout(mainPanel, 0));
	    mainPanel.add(label);
		//einstellung für Hauptstruktur
	    JFrame frame = new JFrame("OOP Projekt PC-Shop");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    frame.setSize(300,300);
	    frame.add(mainPanel);
	    frame.add(southpanel, BorderLayout.SOUTH);
	    frame.setVisible(true);
	}
	
	/**
	 * um den oberen Teil der Oberfläche zu updaten nach Eingabe
	 * Warn Text für falsche Eingaben
	 * @param Warn text falls vorhanden und Haupttext für Labels
	 */
	public static void updateText(String newText) {
		label.setText("<html>"+addWarning+newText+"</html>");
		addWarning = "";
	}
	/**Warn Text für falsche Eingabe oder leere ArrayList
	 * @param wovor das Programm warnt
	 * */
	public static void addWarningText(String text){
		addWarning = "<font color='red'>"+ text +"</font><br>";
	}
	/**um somit nur eingabe zu erhalten nach Eingabe und nicht wenn das Programm idle ist
	 * @return Eingabe des Benutzers (ohe vo-/nach Leerzeichen)
	 * */
	public static String getUserinput(){
		userinput = null;
		while( userinput == null ){
			try {
				Thread.sleep(100);
			}
			catch(Exception e) {}
		}
		return userinput.trim();
	}
	/**Alle Produkte des ArrayList zu zeigen */
	static void showArrayList() {
		textArea.setText("");
		mainPanel.add(pane);
	}
	/**entfernen der Anzeige der Produkte*/
	public static void removeArrayList() {
		mainPanel.remove(pane);
		mainPanel.repaint();
	}
	/**Ein Produkt vom ArrayList hinzufügen 
	 * @param derzeiges Produkt im Loop
	 * */
	static void addToArrayList(String userinput) {
		textArea.setText(textArea.getText()+userinput.trim()+"\n");
	}
}