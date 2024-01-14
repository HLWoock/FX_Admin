package de.woock.ui.view.dialog.mitglieder;

import static javafx.scene.control.ButtonType.CANCEL;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MitgliederDialog {
	 
	public static Dialog<List<MitgliederProStadt>> auswahl() {
		
		// Erstelle den Dialog
		Dialog<List<MitgliederProStadt>> dialog = new Dialog<>();
		dialog.setTitle("Mitgliederbestand");
		dialog.setHeaderText("Anzahl Mitglieder pro Stadt bestimmen");
		
		// Setze den Inhalt des Dialogs
		GridPane gridpane = new GridPane();
		gridpane.setHgap(5); // Spalten
		gridpane.setVgap(5); // Zeilen

		
        // Erstelle Labels und Texte  
		List<String> staedte = List.of("Bremen", "Buchholz", "Hamburg", "Kiel", "Lübeck", "Lüneburg", "Pinneberg", "Reinbek", "Stade");
        List<MitgliederProStadt> list = new ArrayList<>();
        for (int i = 0; i < staedte.size(); i++) {
        	MitgliederProStadt mitgliederProStadt = new MitgliederProStadt(new Label(staedte.get(i)), 
        			                                                       new TextField("0"), 
        			                                                       new CheckBox("loeschen"));
        	list.add(mitgliederProStadt);
        	
            gridpane.add(list.get(i).stadt   , 0, i);
            gridpane.add(list.get(i).anzahl  , 1, i);
            gridpane.add(list.get(i).loeschen, 2, i);
        }
        
		dialog.getDialogPane()
		      .setContent(gridpane);
		
		ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
		
		// Füge Buttons zum Dialog hinzu
		dialog.getDialogPane().getButtonTypes()
		                      .addAll(okButtonType, CANCEL);
		
		// Handle OK Button
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return list;
            }
            return null;
        });
        
        
		return dialog;
	}
}

